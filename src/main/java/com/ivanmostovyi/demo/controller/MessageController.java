package com.ivanmostovyi.demo.controller;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.InboxMessageDto;
import com.ivanmostovyi.demo.dto.MessageFormDto;
import com.ivanmostovyi.demo.dto.OutboxMessageDto;
import com.ivanmostovyi.demo.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/inbox")
    public String getInboxMessages(@AuthenticationPrincipal User user, Model model, @PageableDefault(sort = "date",
            direction = Sort.Direction.DESC) Pageable pageable){

        Page<InboxMessageDto> messagePage = messageService.findAllInboxMessageByReceiverUserId(user.getId(), pageable);

        model.addAttribute("messagePage", messagePage);

        return "messages/inbox";
    }

    @GetMapping("/outbox")
    public String getOutboxMessages(@AuthenticationPrincipal User user, Model model, @PageableDefault(sort = "date",
            direction = Sort.Direction.DESC) Pageable pageable){

        Page<OutboxMessageDto> messagePage = messageService.findAllOutboxMessageBySenderUserId(user.getId(), pageable);

        model.addAttribute("messagePage", messagePage);

        return "messages/outbox";
    }

    @GetMapping("/search")
    public String searchMessages(@AuthenticationPrincipal User user, @RequestParam(name = "search") String search,
                                 @PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable pageable,
                                 Model model){

        model.addAttribute("search", search);
        model.addAttribute(
                "messagePage",
                messageService.findAllInboxMessageByReceiverUserIdWhereTitleContaining(user.getId(), search, pageable)
        );

        return "messages/search";
    }

    @GetMapping("/new")
    public String formCreateMessages(){
        return "messages/form";
    }

    @PostMapping("/new")
    public String createMessages(@AuthenticationPrincipal User user, MessageFormDto messageFormDto,
                                 RedirectAttributes redirectAttributes){

        messageService.create(messageFormDto, user);

        redirectAttributes.addFlashAttribute("isMessageSentSuccessfully", true);

        return "redirect:/messages/inbox";
    }

}
