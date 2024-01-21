package com.nhnacademy.springboot.accountserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountWebSocketController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
