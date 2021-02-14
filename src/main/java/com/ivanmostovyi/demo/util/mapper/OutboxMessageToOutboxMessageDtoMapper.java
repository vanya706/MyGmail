package com.ivanmostovyi.demo.util.mapper;

import com.ivanmostovyi.demo.domain.OutboxMessage;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.OutboxMessageDto;
import com.ivanmostovyi.demo.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OutboxMessageToOutboxMessageDtoMapper {

    private UserRepository userRepository;

    public OutboxMessageToOutboxMessageDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<OutboxMessageDto> map(List<OutboxMessage> outboxMessages) {

        List<User> users = userRepository.findAll();

        return outboxMessages.stream()
                .map(msg -> map(msg, Arrays.asList(msg.getReceiverUsernames()),
                        findUserById(users, msg.getSenderUserId())))
                .collect(Collectors.toList());
    }

    private OutboxMessageDto map(OutboxMessage outboxMessage, List<String> receiverUsernames, User senderUser) {
        return OutboxMessageDto.builder()
                .receiverUsernames(receiverUsernames)
                .senderUsername(senderUser.getUsername())
                .title(outboxMessage.getTitle())
                .body(outboxMessage.getBody())
                .date(outboxMessage.getDate())
                .marked(outboxMessage.isMarked())
                .build();
    }

    private User findUserById(List<User> users, Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User was not found!"));
    }

}
