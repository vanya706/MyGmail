package com.ivanmostovyi.demo.controller;

import com.ivanmostovyi.demo.domain.Message;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/inbox")
    public String getMessagesInbox(@AuthenticationPrincipal User user, Model model){

        List<Message> messages = messageService.findAllByReceiverUserId(user.getId());

        model.addAttribute("inboxMessages", messages);

        return "messages/inbox";
    }

    @GetMapping("/new")
    public String formCreateMessages(){
        return "messages/form";
    }

    @PostMapping("/new")
    public String createMessages(@AuthenticationPrincipal User user, MessageFormDto messageFormDto){

        messageService.create(messageFormDto, user);
        return "redirect:/messages/inbox";
    }

}
