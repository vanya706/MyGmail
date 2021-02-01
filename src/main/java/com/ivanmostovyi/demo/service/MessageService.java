package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageDto;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    void create(MessageFormDto messageFormDto, User senderUser);

    Page<MessageDto> findAllBySenderUserId(Long id, Pageable pageable);

    Page<MessageDto> findAllByReceiverUserId(Long id, Pageable pageable);

    Page<MessageDto> findAllInOutboxAndInboxByUserIdWhereTitleContaining(Long Id, String title, Pageable pageable);

}
