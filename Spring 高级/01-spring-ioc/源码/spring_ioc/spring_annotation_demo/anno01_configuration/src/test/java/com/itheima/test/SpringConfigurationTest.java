package com.itheima.test;

import config.SpringConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试Configuration注解
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringConfigurationTest {

    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        //传入要扫描包的方式
        //1.创建容器
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取对象
//        SpringConfiguration springConfiguration = ac.getBean(SpringConfiguration.class);
//        SpringConfiguration springConfiguration = ac.getBean("springConfiguration",SpringConfiguration.class);
        //3.输出结果
//        System.out.println(springConfiguration);



        //传入被注解的类的字节码的方式
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2.获取对象
        SpringConfiguration springConfiguration = ac.getBean(SpringConfiguration.class);
        //3.输出结果
        System.out.println(springConfiguration);
    }
}
