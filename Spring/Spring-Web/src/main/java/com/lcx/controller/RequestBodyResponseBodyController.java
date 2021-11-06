package com.lcx.controller;

import com.lcx.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RequestBodyResponseBodyController {

    @RequestMapping("/useRequestBody")
    @ResponseBody
    public String useRequestBody(@RequestBody Student user){
        System.out.println("user is "+user);
        return "success";
    }


    @RequestMapping("/useResponseBody")
    public String useResponseBody(){
        return "use response body";
    }
}
