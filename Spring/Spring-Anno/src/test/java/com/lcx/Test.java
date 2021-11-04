package com.lcx;

import com.lcx.config.SpringConfiguration;
import com.lcx.domain.Student;
import com.lcx.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Test {
    @org.junit.Test
    public void Test1(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        StudentService studentService = ac.getBean("studentService", StudentService.class);
//        List<Student> allStudents = studentService.getAllStudents();
//        Student student = studentService.getStudentById(8);
//        System.out.println(student);
        studentService.transfer(8,9,2);

    }
}
