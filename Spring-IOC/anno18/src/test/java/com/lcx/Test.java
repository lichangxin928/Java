package com.lcx;

import com.lcx.config.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class Test {

    @org.junit.Test
    public void Test1(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        DataSource dataSource = ac.getBean("dataSource", DataSource.class);

        System.out.println(dataSource);

    }
}
