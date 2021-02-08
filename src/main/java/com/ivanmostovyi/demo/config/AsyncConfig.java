package com.ivanmostovyi.demo.config;

import com.ivanmostovyi.demo.exception.MessageSendingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@Slf4j
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {

            if (throwable instanceof MessageSendingException) {
                log.error("Error while submission processing", throwable);
            } else {
                log.trace("Trace exception while message async submission processing", throwable);
            }
        };
    }

}
