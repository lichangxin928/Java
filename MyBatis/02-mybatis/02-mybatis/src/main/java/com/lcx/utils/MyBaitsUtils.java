package com.lcx.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBaitsUtils {
    // 获取 SqlSession 的方法
    private static SqlSessionFactory factory = null;
    static {
        String config = "mybatis_config.xml";
        try {
            InputStream in = Resources.getResourceAsStream(config);
             factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        if(factory != null){
            sqlSession = factory.openSession();
        }
        return sqlSession;
    }

}
