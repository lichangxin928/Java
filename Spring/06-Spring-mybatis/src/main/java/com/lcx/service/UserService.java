package com.lcx.service;

import com.lcx.domain.User;

import java.util.List;

public interface UserService {
    int addUser(User user);
    List<User> querySelect();
}
