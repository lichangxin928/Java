package com.lichangxin.service;

import com.lichangxin.domain.User;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return User List集合
     */
    public List<User> findAll();
}
