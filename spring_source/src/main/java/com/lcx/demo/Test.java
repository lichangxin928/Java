package com.lcx.demo;

import com.lcx.demo.Service.User;
import com.lcx.demo.Service.UserService;
import com.lcx.spring.LcxApplicationContext;

public class Test {

    public static void main(String[] args) {

        LcxApplicationContext context = new LcxApplicationContext(AppConfig.class);

        UserService userService = (UserService)context.getBean("userService");

        userService.doSome();
        System.out.println(userService.getUser());
    }
}
