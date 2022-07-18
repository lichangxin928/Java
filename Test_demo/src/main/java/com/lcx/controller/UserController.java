package com.lcx.controller;


import com.lcx.dao.UserJpa;
import com.lcx.domain.User;
import com.lcx.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



@RestController
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/get/{id}")
    public User findById(@PathVariable Integer id){
        long start = System.currentTimeMillis();
        User user = userService.findById(id);
        long end = System.currentTimeMillis();
        System.out.println("mybatis:"+ (end - start));
        return user;
    }

    @GetMapping("/get_jpa/{id}")
    public User findByIdJpa(@PathVariable Integer id){
        long start = System.currentTimeMillis();
        User user = userService.findByIdJpa(id);
        long end = System.currentTimeMillis();
        System.out.println("jpa:"+ (end - start));
        return user ;
    }
}
