package com.itheima.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringDependsOnTest {

    public static void main(String[] args) {
        //1.获取容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.启动容器
        ac.start();
    }
}
