package com.lcx.test;

import com.lcx.dao.UserDao;
import com.lcx.dao.impl.UserDaoImpl;
import com.lcx.domain.User;
import com.lcx.service.UserService;
import com.lcx.service.impl.UserServiceImpl;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        List<User> dao = userDao.findAll();
//        UserService userService = new UserServiceImpl();
//        List<User> all = userService.findAll();
        System.out.println(dao);
    }
}
