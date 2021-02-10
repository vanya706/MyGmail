package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.InboxMessage;
import com.ivanmostovyi.demo.domain.OutboxMessage;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.InboxMessageDto;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.dto.OutboxMessageDto;
import com.ivanmostovyi.demo.repository.InboxMessageRepository;
import com.ivanmostovyi.demo.repository.OutboxMessageRepository;
import com.ivanmostovyi.demo.repository.UserRepository;
import com.ivanmostovyi.demo.util.mapper.InboxMessageToInboxMessageDtoMapper;
import com.ivanmostovyi.demo.util.mapper.OutboxMessageToOutboxMessageDtoMapper;
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

    private InboxMessageRepository inboxMessageRepository;

    private OutboxMessageRepository outboxMessageRepository;

    private InboxMessageToInboxMessageDtoMapper inboxMessageToInboxMessageDtoMapper;

    private OutboxMessageToOutboxMessageDtoMapper outboxMessageToOutboxMessageDtoMapper;

    public MessageServiceImpl(UserRepository userRepository, InboxMessageRepository inboxMessageRepository,
                              OutboxMessageRepository outboxMessageRepository,
                              InboxMessageToInboxMessageDtoMapper inboxMessageToInboxMessageDtoMapper,
                              OutboxMessageToOutboxMessageDtoMapper outboxMessageToOutboxMessageDtoMapper) {

        this.userRepository = userRepository;
        this.inboxMessageRepository = inboxMessageRepository;
        this.outboxMessageRepository = outboxMessageRepository;
        this.inboxMessageToInboxMessageDtoMapper = inboxMessageToInboxMessageDtoMapper;
        this.outboxMessageToOutboxMessageDtoMapper = outboxMessageToOutboxMessageDtoMapper;
    }

    @Async
    @Override
    public void createOutboxMessage(MessageFormDto messageFormDto, String[] receiverUsernames, User senderUser) {

        OutboxMessage outboxMessage = OutboxMessage.builder()
                .marked(false)
                .receiverUsernames(receiverUsernames)
                .title(messageFormDto.getTitle())
                .body(messageFormDto.getBody())
                .senderUserId(senderUser.getId())
                .date(LocalDateTime.now())
                .build();

        outboxMessageRepository.save(outboxMessage);
    }

    @Async
    @Override
    public void createInboxMessage(MessageFormDto messageFormDto, User receiverUser, User senderUser) {

        InboxMessage inboxMessage = InboxMessage.builder()
                .marked(false)
                .read(false)
                .receiverUserId(receiverUser.getId())
                .title(messageFormDto.getTitle())
                .body(messageFormDto.getBody())
                .senderUserId(senderUser.getId())
                .date(LocalDateTime.now())
                .build();

        inboxMessageRepository.save(inboxMessage);
    }

    @Override
    public Page<OutboxMessageDto> findAllOutboxMessageBySenderUserId(Long id, Pageable pageable) {

        List<OutboxMessage> outboxMessages = outboxMessageRepository.findAllBySenderUserId(id, pageable);

        return new PageImpl<>(outboxMessageToOutboxMessageDtoMapper.map(outboxMessages), pageable,
                outboxMessageRepository.countAllBySenderUserId(id));
    }

    @Override
    public Page<InboxMessageDto> findAllInboxMessageByReceiverUserId(Long id, Pageable pageable) {

        List<InboxMessage> inboxMessages = inboxMessageRepository.findAllByReceiverUserId(id, pageable);

        return new PageImpl<>(inboxMessageToInboxMessageDtoMapper.map(inboxMessages), pageable,
                inboxMessageRepository.countAllByReceiverUserId(id));
    }

    @Override
    public Page<InboxMessageDto> findAllInboxMessageByReceiverUserIdWhereTitleContaining(Long Id, String title,
                                                                                         Pageable pageable) {

        List<InboxMessage> messages = inboxMessageRepository
                .findAllByReceiverUserIdAndTitleContaining(Id, title, pageable);

        return new PageImpl<>(
                inboxMessageToInboxMessageDtoMapper.map(messages),
                pageable,
                inboxMessageRepository.countAllByReceiverUserIdAndTitleContaining(Id, title)
        );
    }

}
