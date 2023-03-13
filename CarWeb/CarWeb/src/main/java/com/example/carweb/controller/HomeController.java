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
    @GetMapping("cars/add")
    public String addCar(){
        return "add-car";
    }

}
