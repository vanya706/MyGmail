package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageFormDto;

public interface MessageSendService {

    void sendMessages(MessageFormDto messageFormDto, User senderUser);

}
