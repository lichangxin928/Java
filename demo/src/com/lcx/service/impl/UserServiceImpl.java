package com.lcx.service.impl;

import com.lcx.dao.UserDao;
import com.lcx.dao.impl.UserDaoImpl;
import com.lcx.domain.User;
import com.lcx.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        // 调用 dao
        return dao.findAll();
    }
}
