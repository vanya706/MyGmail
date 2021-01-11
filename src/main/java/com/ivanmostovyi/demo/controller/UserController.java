package com.ivanmostovyi.demo.controller;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.UserDto;
import com.ivanmostovyi.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String create(){
        return "user/form";
    }

    @PostMapping("/new")
    public String create(UserDto userDto, Model model) {

        Optional<User> optionalUser = userService.findByUsername(userDto.getUsername());

        if (optionalUser.isPresent()) {
            model.addAttribute(
                    "userIsExistsMessage",
                    "Користувач з таким логіном вже існує! --> " + optionalUser.get().getUsername()
            );
            return create();
        }

        userService.create(userDto);
        return "redirect:/message/inbox";
    }

}
