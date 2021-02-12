package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.dto.InboxMessageDto;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.dto.OutboxMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    Long GMAIL_SUPPORT_ID = 1L;

    void createInboxMessage(MessageFormDto messageFormDto, Long receiverUserId, Long senderUserId);

    void createOutboxMessage(MessageFormDto messageFormDto, String[] receiverUsernames, Long senderUserId);

    Page<OutboxMessageDto> findAllOutboxMessageBySenderUserId(Long id, Pageable pageable);

    Page<InboxMessageDto> findAllInboxMessageByReceiverUserId(Long id, Pageable pageable);

    Page<InboxMessageDto> findAllInboxMessageByReceiverUserIdWhereTitleContaining(Long Id, String title, Pageable pageable);

}
