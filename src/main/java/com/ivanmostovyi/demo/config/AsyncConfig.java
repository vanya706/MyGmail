package com.ivanmostovyi.demo.config;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@Slf4j
@Configuration
public class AsyncConfig implements AsyncConfigurer, CommandLineRunner {

    private ApplicationContext context;

    private MessageService messageService;

    public AsyncConfig(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) {
        this.messageService = context.getBean(MessageService.class);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {

            log.error("Exception thrown", throwable);

            if (method.getName().equals("createInboxMessage")){

                MessageFormDto messageFormDto = (MessageFormDto) objects[0];
                User receiverUser = (User) objects[1];
                User senderUser = (User) objects[2];

                messageService.createInboxMessage(
                        messageFormDto.toBuilder()
                                .title("Message was not delivered! To user: " + receiverUser.getUsername())
                                .body(throwable.getMessage())
                                .receiverUsername(senderUser.getUsername())
                                .build(),
                        senderUser.getId(),
                        messageService.GMAIL_SUPPORT_ID
                );
            }
        };
    }

}
