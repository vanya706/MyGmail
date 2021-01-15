package com.ivanmostovyi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        if (nonNull(logout)){

            model.addAttribute(
                    "userLogoutMessage",
                    "Ви успішно вийшли з свого аккаунту"
            );
        }

        if (nonNull(error)) {

            model.addAttribute(
                    "incorrectUsernameOrPasswordMessage",
                    "Невірно введений пароль або імя користувача"
            );
        }

        return "login/login";
    }
}
