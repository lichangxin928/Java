package com.lichangxin.test;

import com.lichangxin.dao.UserDao;
import com.lichangxin.dao.impl.UserDaoImpl;
import com.lichangxin.domain.User;
import com.lichangxin.service.UserService;
import com.lichangxin.service.impl.UserServiceImpl;

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
