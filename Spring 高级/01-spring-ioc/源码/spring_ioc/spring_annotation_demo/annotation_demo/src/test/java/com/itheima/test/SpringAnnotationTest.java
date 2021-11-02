package com.itheima.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 测试spring注解驱动开发的入门案例
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringAnnotationTest {

    public static void main(String[] args) {
        //1.创建容器（基于注解的创建方式）
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.根据bean的Id获取对象
        JdbcTemplate jdbcTemplate = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        //3.执行操作
        jdbcTemplate.update("insert into account(name,money)values(?,?)","zzz",54321);
    }
}
