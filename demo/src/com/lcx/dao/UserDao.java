package com.lcx.dao;

import com.lcx.domain.User;

import java.util.List;

public interface UserDao {
    /**
     *
     * @return
     */
    public List<User> findAll();
}
