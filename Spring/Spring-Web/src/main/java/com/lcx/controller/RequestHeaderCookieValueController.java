package com.lcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RequestHeaderCookieValueController {

    @RequestMapping("/useRequestHeader")
    public String useRequestHeader(@RequestHeader(value = "Tccept-Language",required = false,defaultValue = "test") String header){
        System.out.println("Accept-Language:"+header);
        return "success";
    }

    @RequestMapping("/useCookieValue")
    public String useCookieValue(@CookieValue("JSESSIONID") String jsessionid){
        System.out.println(jsessionid);
        return "success";
    }
}
