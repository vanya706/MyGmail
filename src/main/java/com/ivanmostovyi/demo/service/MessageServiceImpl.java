package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.Message;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageDto;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.exception.MessageSendingException;
import com.ivanmostovyi.demo.repository.MessageRepository;
import com.ivanmostovyi.demo.repository.UserRepository;
import com.ivanmostovyi.demo.util.mapper.MessageToMessageDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private UserRepository userRepository;

    private MessageRepository messageRepository;

    private MessageToMessageDtoMapper messageToMessageDtoMapper;

    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository,
                              MessageToMessageDtoMapper messageToMessageDtoMapper) {

        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.messageToMessageDtoMapper = messageToMessageDtoMapper;
    }

    @Async
    @Override
    public void create(MessageFormDto messageFormDto, User senderUser){

        User receiverUser = userRepository.findByUsername(messageFormDto.getReceiverUsername())
                                          .orElseThrow(() -> new MessageSendingException("Receiver was not found!"));

        Message message = Message.builder()
                .marked(false)
                .read(false)
                .receiverUserId(receiverUser.getId())
                .title(messageFormDto.getTitle())
                .body(messageFormDto.getBody())
                .senderUserId(senderUser.getId())
                .date(LocalDateTime.now())
                .build();

        messageRepository.save(message);
    }

    @Override
    public Page<MessageDto> findAllBySenderUserId(Long id, Pageable pageable) {

        List<Message> messages = messageRepository.findAllBySenderUserId(id, pageable);

        return new PageImpl<>(messageToMessageDtoMapper.map(messages), pageable,
                messageRepository.countAllBySenderUserId(id));
    }

    @Override
    public Page<MessageDto> findAllByReceiverUserId(Long id, Pageable pageable) {

        List<Message> messages = messageRepository.findAllByReceiverUserId(id, pageable);

        return new PageImpl<>(messageToMessageDtoMapper.map(messages), pageable,
                messageRepository.countAllByReceiverUserId(id));
    }

    @Override
    public Page<MessageDto> findAllInOutboxAndInboxByUserIdWhereTitleContaining(Long Id, String title,
                                                                                Pageable pageable) {

        List<Message> messages = messageRepository.findAllByReceiverUserIdAndTitleContainingOrSenderUserIdAndTitleContaining(
                Id, title, Id, title, pageable
        );

        return new PageImpl<>(
                messageToMessageDtoMapper.map(messages),
                pageable,
                messageRepository.countAllByReceiverUserIdAndTitleContainingOrSenderUserIdAndTitleContaining(Id,title, Id, title)
        );
    }

}
