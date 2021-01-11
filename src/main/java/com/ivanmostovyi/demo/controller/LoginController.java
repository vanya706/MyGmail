package com.ivanmostovyi.demo.controller;

import com.ivanmostovyi.demo.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "logout", required = false) String logout, Model model) {

        if (logout != null && logout.equals("")){

            model.addAttribute(
                    "userLogoutMessage",
                    "Ви успішно вийшли з свого аккаунту"
            );
        }

        return "login/login";
    }

    @PostMapping("/login")
    public String redirect(@RequestParam(value = "error", required = false) String error,
                           UserDto userDto, Model model) {

        if (error != null && error.equals("true")) {

            model.addAttribute(
                    "incorrectUsernameOrPasswordMessage",
                    "Невірно введений пароль або імя користувача"
            );
            return "login/login";
        }

        return "redirect:/message/inbox";
    }

}
