package com.lcx;

import com.lcx.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws IOException {
        /**
         * 主要类
         *      1. Resources:mybatis 中的一个累，负责读取主配置文件
         *          InputStream in = Resources.getResourcesAsStream("");
         *      2. SqlSessionFactoryBuilder : 创建 SqlSessionFactory 对象
         *          SqlSessionFactoryBuilder builder = new ....
         *          SqlSessionFactory factory = builder.build(in)
         *      3. SqlSessionFactory 重量级对象，程序常见一个对象耗时比较长，使用资源比较多
         *          在整个项目中，有一个就足够了
         *          SqlSessionFactory：接口 接口实现类：DefaultSqlSessionFactory
         *          SqlSessionFactory：作用 获取SqlSession对象。SqlSession sqlSession = factory。openSession()
         *
         *          openSession() 方法说明
         *              1. openSession() ： 无参数的，获取是飞自动提交事务的SqlSession 对象
         *              2. openSession(boolean)：传入参数是否自动提交事务
         *      4. SqlSession
         *          SqlSession接口：定义了操作数据库的方法
         *          SqlSession接口的实现累 DefaultSqlSession
         *
         *          使用要求：
         *              非线程安全的，使用前使用 openSession() 使用后执行 SqlSession.close()
         *
         */
    }

}
