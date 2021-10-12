package com.lichangxin.service.impl;

import com.lichangxin.dao.UserDao;
import com.lichangxin.dao.impl.UserDaoImpl;
import com.lichangxin.domain.User;
import com.lichangxin.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        // 调用 dao
        return dao.findAll();
    }
}
