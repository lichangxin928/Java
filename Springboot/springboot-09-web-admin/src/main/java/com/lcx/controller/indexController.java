package com.lcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class indexController {

    @GetMapping({"/","/login"})
    public String loginPage(){

        return "login";
    }
    @PostMapping("/login")
    public String main(String username,String password){

        System.out.println(username + " and " + password);
        return "main";
    }
}
