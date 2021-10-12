package com.lcx.dao.impl;

import com.lcx.dao.UserDao;
import com.lcx.domain.User;
import com.lcx.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        // 操作数据库
        // 1. 定义 sql
        String sql = "select * from user";
        List<User> users = template.query(sql,new BeanPropertyRowMapper<>(User.class));
        return users;
    }
}
