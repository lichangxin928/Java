package com.lcx.controller;

import com.lcx.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class indexController {

    @GetMapping({"/","/login"})
    public String loginPage(){

        return "login";
    }
    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){

        if(!StringUtils.isEmpty(user.getUserName()) && "123456".equals(user.getPassword())){
            // 登录成功，重定向到 main 页面，防止表单重新提交
            session.setAttribute("loginUser",user);
            return "redirect:/main.html";
        }else{
            // 登录失败，返回登录页面
            model.addAttribute("msg","账号或密码错误");
            return "login";
        }

    }
    @GetMapping("/main.html")
    public String mainPage(HttpSession session){
        // 判断是否登录了
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null){
            return "main";
        }
        return "redirect:/";
    }
}
