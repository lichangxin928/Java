package com.lcx.controller;

import com.lcx.domain.Student;
import com.lcx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/test")
    public String findAll(){
        List<Student> all = studentService.findAll();
        System.out.println(all);
        return "success";
    }

    @RequestMapping("/studentListPage")
    public String studentListPage(){
        return "redirect:/studentList";
    }

    @ResponseBody
    @RequestMapping("/studentList")
    public List<Student> getStudentList(){
        List<Student> all = studentService.findAll();
        return all;
    }
}
