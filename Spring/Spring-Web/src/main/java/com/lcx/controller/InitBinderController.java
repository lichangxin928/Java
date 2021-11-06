package com.lcx.controller;

import com.lcx.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitBinderController {

    @RequestMapping("/useInitBinder")
    public String useInitBinder(Student user){
        System.out.println("user is "+user);
        return "success";
    }

    /**
     * 初始化数据绑定器
     * @param dataBinder

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }*/
}
