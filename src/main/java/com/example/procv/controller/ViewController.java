package com.example.procv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }

}
