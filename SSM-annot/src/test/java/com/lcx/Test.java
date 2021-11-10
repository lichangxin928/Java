package com.lcx;

import com.lcx.config.SpringConfig;
import com.lcx.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    @org.junit.Test
    public void Test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);
        StudentService bean = app.getBean(StudentService.class);
        System.out.println(bean.findAll());
    }
}
