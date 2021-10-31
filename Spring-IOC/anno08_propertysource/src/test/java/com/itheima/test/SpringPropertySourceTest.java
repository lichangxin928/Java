package com.itheima.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 测试类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringPropertySourceTest{

    public static void main(String[] args) throws Exception{
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取对象
        DataSource dataSource = ac.getBean("dataSource",DataSource.class);
        //3.获取连接
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
