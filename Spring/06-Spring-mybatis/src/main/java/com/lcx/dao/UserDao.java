package com.lcx.dao;

import com.lcx.domain.User;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    List<User> selectUser();
}
