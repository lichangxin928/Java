package com.lcx.controller;

import com.lcx.exception.CustomerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ExceptionHandler注解的讲解
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Controller
public class ExceptionHandlerController {

    @RequestMapping("/useExceptionHandler")
    public String useExceptionHandler(Integer age)throws Exception{
        if(age == null){
            throw new NullPointerException();
        }
        if(age>100){
            throw new CustomerException("超过年龄限定");
        }
        System.out.println("年龄是："+age);
        return "success";
    }



}
