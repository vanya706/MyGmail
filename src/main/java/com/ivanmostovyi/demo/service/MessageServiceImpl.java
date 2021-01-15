package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.Message;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.exception.MessageSendingException;
import com.ivanmostovyi.demo.repository.MessageRepository;
import com.ivanmostovyi.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private UserRepository userRepository;

    private MessageRepository messageRepository;

    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void create(MessageFormDto messageFormDto, User user){

        User receiverUser = userRepository.findByUsername(messageFormDto.getReceiverUsername())
                                          .orElseThrow(() -> new MessageSendingException("Receiver not found!"));

        Message message = Message.builder()
                .marked(false)
                .read(false)
                .receiverUserId(receiverUser.getId())
                .title(messageFormDto.getTitle())
                .body(messageFormDto.getBody())
                .senderUserId(user.getId())
                .build();

        messageRepository.save(message);
    }

    @Override
    public List<Message> findAllBySenderUserId(Long id) {
        return messageRepository.findAllBySenderUserId(id);
    }

    @Override
    public List<Message> findAllByReceiverUserId(Long id) {
        return messageRepository.findAllByReceiverUserId(id);
    }
}
