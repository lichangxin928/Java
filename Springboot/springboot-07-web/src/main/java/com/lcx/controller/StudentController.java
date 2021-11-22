package com.lcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class StudentController {

    @RequestMapping("/student")
    public ModelAndView getStudent(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/Test/index.html");
        return mv;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getUser(){
        return "GET-张三";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String postUser(){
        return "POST-张三";
    }
    @RequestMapping(method = RequestMethod.PUT)
    public String putUser(){
        return "put-张三";
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteUser(){
        return "DELETE-张三";
    }



}
