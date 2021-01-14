package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.Messages;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessagesFormDto;
import com.ivanmostovyi.demo.repository.MessagesRepository;
import com.ivanmostovyi.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private UserRepository userRepository;

    private MessagesRepository messagesRepository;

    public MessageServiceImpl(UserRepository userRepository, MessagesRepository messagesRepository) {
        this.userRepository = userRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public void create(MessagesFormDto messagesFormDto){

        Optional<User> receiverUser = userRepository.findByUsername(messagesFormDto.getReceiverUsername());

        receiverUser.orElseThrow(() -> new UsernameNotFoundException("Receiver not found!"));

        Messages messages = Messages.builder()
                .marked(false)
                .read(false)
                .receiverId(receiverUser.get().getId())
                .title(messagesFormDto.getTitle())
                .body(messagesFormDto.getBody())
                .userId(messagesFormDto.getUserId())
                .build();

        messagesRepository.save(messages);
    }

    @Override
    public List<Messages> findAllByUserId(Long id) {
        return messagesRepository.findAllByUserId(id);
    }

    @Override
    public List<Messages> findAllByReceiverId(Long id) {
        return messagesRepository.findAllByReceiverId(id);
    }
}
