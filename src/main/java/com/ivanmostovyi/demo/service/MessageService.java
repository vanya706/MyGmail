package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.Message;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageFormDto;

import java.util.List;

public interface MessageService {

    void create(MessageFormDto messageFormDto, User user);

    List<Message> findAllBySenderUserId(Long id);

    List<Message> findAllByReceiverUserId(Long id);

}
