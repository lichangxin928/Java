package com.lcx;

import com.lcx.config.SpringConfiguration;
import com.lcx.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    @org.junit.Test
    public void Test() {
        //1. 创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2. 获取对象
        StudentService studentService = ac.getBean("studentServiceImpl", StudentService.class);
        //3. 输出
        studentService.saveStudent();
    }
}
