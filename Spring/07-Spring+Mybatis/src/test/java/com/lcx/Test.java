package com.lcx;

import com.lcx.dao.UserDao;
import com.lcx.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    @org.junit.Test
    public void Test1(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        UserDao userDao = (UserDao) ac.getBean("userDao");
        for (User user : userDao.selectUser()) {
            System.out.println(user);
        }

    }
}
