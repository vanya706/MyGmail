package com.ivanmostovyi.demo.controller;

import com.ivanmostovyi.demo.dto.UserFormDto;
import com.ivanmostovyi.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String create(){
        return "user/form";
    }

    @PostMapping("/register")
    public String create(UserFormDto userFormDto) {

        userService.create(userFormDto);
        return "redirect:/message/inbox";
    }

}
