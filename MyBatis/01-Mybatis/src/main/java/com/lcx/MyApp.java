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
        // 1. 定义主配置文件名称,从类的更路径开始(target/classes)
        String config = "mybatis.xml";
        // 2. 读取这个 config 表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        // 3. 创建 SqlSessionFactoryBuilder 对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 4. 创建 SqlSessionFactory 对象
        SqlSessionFactory factory = builder.build(in);
        // 5. 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 6. 指定要执行的 sql 语句的标识 sql 映射文件中的 namespace + "." + 标签的id
        String sql_id = "com.lcx.dao.UserDao" + "." + "findAll";
        // 7. 执行 Sql 语句 通过 sqlId找到语句
        List<User> users = sqlSession.selectList(sql_id);
        users.forEach(user -> System.out.println(user));
        sqlSession.close();
    }

}
