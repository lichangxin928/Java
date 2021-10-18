package com.lcx;

import com.lcx.domain.User;
import com.lcx.utils.MyBaitsUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMyBatis {

    @Test
    public void testSelect(){
        SqlSession sqlSession = MyBaitsUtils.getSqlSession();
        String sql_id = "com.lcx.dao.UserDao" + "." + "findAll";
        // 7. 执行 Sql 语句 通过 sqlId找到语句
        List<User> users = sqlSession.selectList(sql_id);
        users.forEach(user -> System.out.println(user));
        sqlSession.close();
    }
    @Test
    public void testInsert(){
        SqlSession sqlSession = MyBaitsUtils.getSqlSession();
        String sqlId = "com.lcx.dao.UserDao."+ "insertUser";
        User user = new User();
        user.setId(2);
        user.setName("lcx");
        user.setGender("female");
        user.setAge(18);
        user.setAddress("chengdu");
        user.setQq("2431424266");
        user.setEmail("2431424266@qq.com");

        int value = sqlSession.insert(sqlId, user);
        System.out.println(value);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
