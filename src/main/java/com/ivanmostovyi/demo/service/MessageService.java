package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.InboxMessageDto;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.dto.OutboxMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    void create(MessageFormDto messageFormDto, User senderUser);

    Page<OutboxMessageDto> findAllOutboxMessageBySenderUserId(Long id, Pageable pageable);

    Page<InboxMessageDto> findAllInboxMessageByReceiverUserId(Long id, Pageable pageable);

    Page<InboxMessageDto> findAllInboxMessageByReceiverUserIdWhereTitleContaining(Long Id, String title, Pageable pageable);

}
