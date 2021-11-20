package com.lcx;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcx.dao.StudentDao;
import com.lcx.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot04MybatisplusApplicationTests {

    @Autowired
    StudentDao studentDao;
    @Test
    void contextLoads() {
        Student student = studentDao.selectById(8);
        System.out.println(student);
    }

}
