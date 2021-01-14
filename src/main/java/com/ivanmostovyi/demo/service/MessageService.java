package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.Messages;
import com.ivanmostovyi.demo.dto.MessagesFormDto;

import java.util.List;

public interface MessageService {

    void create(MessagesFormDto messagesFormDto);

    List<Messages> findAllByUserId(Long id);

    List<Messages> findAllByReceiverId(Long id);

}
