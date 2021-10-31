package com.itheima.test;

import com.itheima.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringPrimaryTest {
    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取service对象
        AccountService accountService = ac.getBean("accountService",AccountService.class);
        //3.执行方法
        accountService.saveAccount();
    }
}
