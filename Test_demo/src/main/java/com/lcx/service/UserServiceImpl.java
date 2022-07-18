package com.lcx.service;

import com.lcx.dao.UserJpa;
import com.lcx.dao.UserMapper;
import com.lcx.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserJpa userJpa;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByIdJpa(Integer id) {
        return userJpa.findById(id).get();
    }
}
