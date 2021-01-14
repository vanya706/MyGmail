package com.ivanmostovyi.demo.controller;

import com.ivanmostovyi.demo.domain.Messages;
import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.MessagesFormDto;
import com.ivanmostovyi.demo.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/messages")
public class MessagesController {

    private MessageService messageService;

    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/inbox")
    public String getMessagesInbox(@AuthenticationPrincipal User user, Model model){

        List<Messages> messagesList = messageService.findAllByReceiverId(user.getId());

        model.addAttribute("inboxMessagesList", messagesList);

        return "messages/inbox";
    }

    @GetMapping("/new")
    public String formCreateMessages(){
        return "messages/form";
    }

    @PostMapping("/new")
    public String createMessages(@AuthenticationPrincipal User user, MessagesFormDto messagesFormDto){

        messagesFormDto.setUserId(user.getId());
        messageService.create(messagesFormDto);
        return "redirect:/messages/inbox";
    }

}
