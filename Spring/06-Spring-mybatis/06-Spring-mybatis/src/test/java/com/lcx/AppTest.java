package com.lcx;

import static org.junit.Assert.assertTrue;

import com.lcx.dao.UserDao;
import com.lcx.domain.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AppTest {
    @Test
    public void Test1() {
        String config = "applicationContext.xml";
        ClassPathXmlApplicationContext cc = new ClassPathXmlApplicationContext(config);
        String names[] = cc.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void Test2() {
        String config = "applicationContext.xml";
        ClassPathXmlApplicationContext cc = new ClassPathXmlApplicationContext(config);
        UserDao userDao = (UserDao) cc.getBean("userDao");
        List<User> users = userDao.selectUser();
        System.out.println(users);

    }
}
