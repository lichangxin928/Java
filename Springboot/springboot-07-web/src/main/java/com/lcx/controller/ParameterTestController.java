package com.lcx.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    @RequestMapping("/car/{id}/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String username,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String user_Agent,
                                     @RequestHeader Map<String,String> head,
                                     @RequestParam("age") Integer age,
                                     @RequestParam Map<String,String> param,
                                     @CookieValue(required = false) Cookie cookie,
                                     HttpServletRequest request){

        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("username",username);
        map.put("pv",pv);
        map.put("user_Agent",user_Agent);
//        map.put("head",head);
        map.put("age",age);
        map.put("param",param);
        return map;
    }


}
