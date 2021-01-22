package com.ivanmostovyi.demo.exception.handler;

import com.ivanmostovyi.demo.exception.MessageSendingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.ivanmostovyi.demo.util.FlashMessageConstants.FLASH_MESSAGE_ERROR;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(MessageSendingException.class)
    public String handleMessageSendingException(MessageSendingException messageSendingException,
                                                RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute(FLASH_MESSAGE_ERROR, messageSendingException.getMessage());

        return "redirect:/messages/send/error";
    }

    @ExceptionHandler(Exception.class)
    public String unhandledException(){
        return "redirect:/";
    }

}
