package com.lcx.controller;


import com.lcx.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 该类的公共模块的地址放在此处
 */
//@RequestMapping("/test")
@Controller
public class MyController {
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String name,Integer age){        // doGet() --- service 请求处理
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("age",age);
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping("/doStudent.do")
    @ResponseBody
    public Student doStudentResult(String name,Integer age){
        // 调用 Service 结果请求数据
        Student student = new Student();
        student.setName("lisi");
        student.setAge(20);
        return student;
    }
}
