package com.lichangxin.dao;

import com.lichangxin.domain.User;

import java.util.List;

public interface UserDao {
    /**
     *
     * @return
     */
    public List<User> findAll();
}
