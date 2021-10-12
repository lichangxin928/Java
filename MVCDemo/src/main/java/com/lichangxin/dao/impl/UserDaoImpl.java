package com.lichangxin.dao.impl;

import com.lichangxin.dao.UserDao;
import com.lichangxin.domain.User;
import com.lichangxin.util.JDBCUtils;
import com.lichangxin.util.JDBCUtils1;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils1.getDataSource());
    @Override
    public List<User> findAll() {
        // 操作数据库
        // 1. 定义 sql
        String sql = "select * from user";
        System.out.println(User.class);
        List<User> users = template.query(sql,new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
        return users;
    }
}
