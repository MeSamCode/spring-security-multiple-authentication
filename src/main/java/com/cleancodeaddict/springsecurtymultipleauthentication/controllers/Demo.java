package com.cleancodeaddict.springsecurtymultipleauthentication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/security")
public class Demo {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring security lovers!";
    }
}
