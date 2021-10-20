package com.lcx;

import static org.junit.Assert.assertTrue;

import com.lcx.service.SomeService;
import com.lcx.service.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void Test1(){
        // 传统方法
        SomeService service = new SomeServiceImpl();
        service.doSome();
    }

    /**
     * spring 默认创建容器的时间是在 创建容器时创建所有对象
     */
    @Test
    public void Test2(){
        // IOC
        // 1，指定 spring 配置文件名称
        String config = "spring_config.xml";
        // 2. 创建表示spring 容器的对象。 applicationContext
        // ApplicationContext 就是表示 spring 的容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
        SomeService someService = (SomeService) applicationContext.getBean("someService");
        someService.doSome();
    }
}
