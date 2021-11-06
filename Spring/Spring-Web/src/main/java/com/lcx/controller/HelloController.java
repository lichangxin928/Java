package com.lcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ModeAttribute
 * 当修饰方法时，表示在每次请求都会先执行这个方法，当修饰参数时，表示将Mode 交给一个控制方法
 * 能够存入值，也能取出值。
 *
 * @SessionAttribute 和 @SessionAttribute
 * 能够获取Session 和添加 Session
 *
 * @ExceptionHandler 展示错误
 */
@Controller
public class HelloController {

    @ModelAttribute("test")
    public String showMode(){
        return "test";
    }

    @RequestMapping("/hello")
    public String sayHello(@ModelAttribute("test") String name){
        System.out.println("控制器方法执行了");
        System.out.println(name);
        return "success";
    }
}
