package com.lcx;

import com.lcx.config.SpringConfig;
import com.lcx.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    @org.junit.Test
    public void Test1(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        StudentService bean = applicationContext.getBean(StudentService.class);
        bean.findAll();
    }
}
