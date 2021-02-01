package com.ivanmostovyi.demo.util.mapper;

import com.ivanmostovyi.demo.domain.Message;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageDto;
import com.ivanmostovyi.demo.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageToMessageDtoMapper {

    private UserRepository userRepository;

    public MessageToMessageDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<MessageDto> map(List<Message> messages) {

        List<User> users = userRepository.findAll();

        return messages.stream()
                .map(msg -> map(msg, findUserById(
                        users, msg.getReceiverUserId()),findUserById(users, msg.getSenderUserId())))
                .collect(Collectors.toList());
    }

    private MessageDto map(Message message, User receiverUser, User senderUser) {
        return MessageDto.builder()
                .receiverUsername(receiverUser.getUsername())
                .senderUsername(senderUser.getUsername())
                .title(message.getTitle())
                .body(message.getBody())
                .date(message.getDate())
                .marked(message.isMarked())
                .read(message.isRead())
                .build();
    }

    private User findUserById(List<User> users, Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User was not found!"));
    }

}
