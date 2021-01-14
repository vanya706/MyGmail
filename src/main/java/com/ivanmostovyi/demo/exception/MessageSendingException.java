package com.ivanmostovyi.demo.exception;

public class MessageSendingException extends RuntimeException {

    public MessageSendingException(String message) {
        super(message);
    }
}
