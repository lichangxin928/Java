package com.itheima.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 测试Bean注解的使用
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringBeanTest {

    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取对象
        //DataSource dataSource = ac.getBean("dataSource",DataSource.class);
        //3.输出
        //System.out.println(dataSource);

        //获取JdbcTemplate对象
        JdbcTemplate jdbcTemplate = ac.getBean("createJdbcTemplate", JdbcTemplate.class);
        //输出jdbcTemplate中的数据源
        System.out.println(jdbcTemplate);
    }
}
