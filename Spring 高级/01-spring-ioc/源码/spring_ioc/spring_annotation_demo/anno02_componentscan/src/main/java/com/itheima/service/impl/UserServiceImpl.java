package com.itheima.service.impl;

import com.itheima.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public void saveUser() {
        System.out.println("实现了保存用户");
    }
}
