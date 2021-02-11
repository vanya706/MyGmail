package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.exception.MessageSendingException;
import com.ivanmostovyi.demo.repository.UserRepository;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MessageSendServiceImpl implements MessageSendService {

    private MessageService messageService;

    private UserRepository userRepository;

    private Environment environment;

    public MessageSendServiceImpl(MessageService messageService,
                                  UserRepository userRepository,
                                  Environment environment) {

        this.messageService = messageService;
        this.userRepository = userRepository;
        this.environment = environment;
    }

    @Async
    @Override
    public void sendMessages(MessageFormDto messageFormDto, User senderUser) {

        String[] receiverUsernames = messageFormDto.getReceiverUsername().split(" ");

        messageService.createOutboxMessage(messageFormDto, receiverUsernames, senderUser);

        List<User> receiverUsers = new ArrayList<>();
        List<String> notFoundUsernames = new ArrayList<>();

        List<User> allUsers = userRepository.findAll();

        Arrays.stream(receiverUsernames).forEach(
                username -> findUserByUsername(allUsers, username)
                        .ifPresentOrElse(receiverUsers::add, () -> notFoundUsernames.add(username))
        );

        receiverUsers.forEach(
                receiverUser -> messageService.createInboxMessage(
                        messageFormDto, receiverUser, senderUser
                )
        );

        if (!notFoundUsernames.isEmpty()) {

            String adminUsername = environment.getProperty("admin.default.username");

            User adminUser = userRepository.findByUsername(adminUsername)
                    .orElseThrow(() -> new MessageSendingException(
                            String.format("Admin with username \"%s\" was not found", adminUsername)));

            messageService.createInboxMessage(
                    messageFormDto.toBuilder()
                            .title("Message was not delivered!")
                            .body(String.format("To %d receiver(s), not found: %s",
                                    notFoundUsernames.size(), String.join(", ", notFoundUsernames)))
                            .receiverUsername(senderUser.getUsername())
                            .build(),
                    senderUser,
                    adminUser
            );
        }
    }

    private Optional<User> findUserByUsername(List<User> users, String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

}
