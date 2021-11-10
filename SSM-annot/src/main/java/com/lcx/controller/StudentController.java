package com.lcx.controller;

import com.lcx.domain.Student;
import com.lcx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/test")
    public String findAll(){
        List<Student> all = studentService.findAll();
        System.out.println(all);
        System.out.println("mode");
        return "success";
    }
}
