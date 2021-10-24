package com.lcx;

import com.lcx.bao2.SomeService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void shouldAnswerWithTrue() {
        String config = "applicationContext.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        SomeService someService = (SomeService) context.getBean("someService");
        someService.doSome();
    }
    @Test
    public void Test2(){
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("applicationContext2.xml");
        SomeService myService = (SomeService) cp.getBean("myService");
        int i = myService.doSome();
        System.out.println(i);
    }
    @Test
    public void Test3(){
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("applicationContext3.xml");
        com.lcx.bao3.SomeService ss = (com.lcx.bao3.SomeService) cp.getBean("someService");
        int i = ss.doSome();
        System.out.println(i);

    }
}
