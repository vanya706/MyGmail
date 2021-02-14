package com.ivanmostovyi.demo.util.mapper;

import com.ivanmostovyi.demo.domain.InboxMessage;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.InboxMessageDto;
import com.ivanmostovyi.demo.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InboxMessageToInboxMessageDtoMapper {

    private UserRepository userRepository;

    public InboxMessageToInboxMessageDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<InboxMessageDto> map(List<InboxMessage> inboxMessages) {

        List<User> users = userRepository.findAll();

        return inboxMessages.stream()
                .map(msg -> map(msg, findUserById(
                        users, msg.getReceiverUserId()),findUserById(users, msg.getSenderUserId())))
                .collect(Collectors.toList());
    }

    private InboxMessageDto map(InboxMessage inboxMessage, User receiverUser, User senderUser) {
        return InboxMessageDto.builder()
                .receiverUsername(receiverUser.getUsername())
                .senderUsername(senderUser.getUsername())
                .title(inboxMessage.getTitle())
                .body(inboxMessage.getBody())
                .date(inboxMessage.getDate())
                .marked(inboxMessage.isMarked())
                .read(inboxMessage.isRead())
                .build();
    }

    private User findUserById(List<User> users, Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User was not found!"));
    }

}
