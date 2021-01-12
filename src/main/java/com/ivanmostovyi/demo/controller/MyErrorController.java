package com.ivanmostovyi.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.nonNull;

@Controller
public class MyErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(Model model, HttpServletRequest request){

        Integer errorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        model.addAttribute("errorCode", errorCode);

        if (nonNull(exception)) {
            model.addAttribute("errorMessage", exception.getMessage());
        }

        return "error/show";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
