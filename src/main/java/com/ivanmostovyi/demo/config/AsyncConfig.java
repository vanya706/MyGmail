package com.ivanmostovyi.demo.config;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.exception.MessageSendingException;
import com.ivanmostovyi.demo.service.MessageService;
import com.ivanmostovyi.demo.service.UserService;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@Configuration
public class AsyncConfig implements AsyncConfigurer {

    private ApplicationContext context;

    public AsyncConfig(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {

            if (throwable instanceof MessageSendingException){

                MessageService messageService = context.getBean(MessageService.class);
                UserService userService = context.getBean(UserService.class);

                MessageFormDto messageFormDto = (MessageFormDto) objects[0];
                User user = (User) objects[1];

                messageService.create(
                        messageFormDto.toBuilder()
                                .title(throwable.getMessage())
                                .body("Message wasn't delivered to user: " + messageFormDto.getReceiverUsername())
                                .receiverUsername(user.getUsername())
                                .build(),
                        userService.findByUsername("Gmail Support")
                );
            }
        };
    }

}
