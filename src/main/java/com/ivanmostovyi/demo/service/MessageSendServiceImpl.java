package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.exception.MessageSendingException;
import com.ivanmostovyi.demo.repository.UserRepository;
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

    public MessageSendServiceImpl(MessageService messageService, UserRepository userRepository) {

        this.messageService = messageService;
        this.userRepository = userRepository;
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

            User gmail_support = userRepository.findByUsername("Gmail Support")
                    .orElseThrow(() -> new MessageSendingException("Sender with name \"Gmail Support\" was not found"));

            messageService.createInboxMessage(
                    messageFormDto.toBuilder()
                            .title("Message was not delivered!")
                            .body(String.format("To %d receiver(s), not found: %s",
                                    notFoundUsernames.size(), String.join(", ", notFoundUsernames)))
                            .receiverUsername(senderUser.getUsername())
                            .build(),
                    senderUser,
                    gmail_support
            );
        }
    }

    private Optional<User> findUserByUsername(List<User> users, String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

}
