package com.itheima.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringConditionalTest {

    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取对象
        DataSource dataSource = ac.getBean("dataSource",DataSource.class);
        //3.输出
        System.out.println(dataSource);
    }
}
