package com.lcx;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.lcx.dao.StudentDao;
import com.lcx.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMain {
    @Test
    public void Test() throws IOException {
        String config = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        List<Student> students = mapper.selectList(null);
        for (Student student : students) {
            System.out.println(student);
        }


    }
}
