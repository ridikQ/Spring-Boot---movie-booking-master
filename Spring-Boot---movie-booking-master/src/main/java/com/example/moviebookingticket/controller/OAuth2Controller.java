package com.example.moviebookingticket.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {
    @GetMapping("/")
    public String secured() {
        return "Hello, Secured!";
    }

}

