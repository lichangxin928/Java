package com.lcx.service.impl;

import com.lcx.dao.UserDao;
import com.lcx.domain.User;
import com.lcx.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {

    }

    @Override
    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public List<User> querySelect() {

        return userDao.selectUser();
    }
}
