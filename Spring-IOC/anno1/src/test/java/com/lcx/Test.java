package com.lcx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test {
    public static void main(String[] args) {
        //1. 创建容器，基于注解的方式
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.lcx.config");
        //2. 根据bean id获取对象
        JdbcTemplate jdbc = ac.getBean("createJdbcTemplate",JdbcTemplate.class);
        //3. 执行操作
        jdbc.update("insert into student(name,age) values(?,?) ","张三",12);


    }
}
