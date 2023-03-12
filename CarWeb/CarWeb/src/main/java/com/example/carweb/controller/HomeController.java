package com.example.carweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String index(){
        return "index";
    }
    @GetMapping("add-car")
    public String addCar(){
        return "add-car";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @GetMapping("profile")
    public String profile(){
        return "profile";
    }
    @GetMapping("admin")
    public String admin(){
        return "admin";
    }
}
