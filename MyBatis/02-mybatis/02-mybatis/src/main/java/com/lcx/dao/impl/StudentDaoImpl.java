package com.lcx.dao.impl;

import com.lcx.dao.StudentDao;
import com.lcx.domain.User;
import com.lcx.utils.MyBaitsUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<User> userList() {
        SqlSession sqlSession = MyBaitsUtils.getSqlSession();
        String sqlId = "com.lcx.dao.StudentDao.userList";
        List<User> list = sqlSession.selectList(sqlId);
        return list;
    }
}
