package com.lcx.controller;

import com.lcx.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ResponseTestController {

    @ResponseBody
    @GetMapping("/student")
    public Student getStudent(){
        Student student = new Student();
        student.setAge(16);
        student.setId(1);
        student.setName("zangsan");
        return student;
    }
}
