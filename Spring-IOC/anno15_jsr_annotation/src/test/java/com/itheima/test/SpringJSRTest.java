package com.itheima.test;

import com.itheima.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringJSRTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        accountService.saveAccount();
    }
}
