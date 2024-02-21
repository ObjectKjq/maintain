package com.kjq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/haha")
    public String haha(){
        return "qwe";
    }
}
