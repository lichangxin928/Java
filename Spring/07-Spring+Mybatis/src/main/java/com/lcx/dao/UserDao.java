package com.lcx.dao;

import com.lcx.domain.User;

import java.util.List;

public interface UserDao {
    List<User> selectUser();
    int insertUser(User user);
}
