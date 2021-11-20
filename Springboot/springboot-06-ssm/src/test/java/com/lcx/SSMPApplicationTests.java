package com.lcx;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcx.dao.StudentDao;
import com.lcx.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SSMPApplicationTests {
    @Autowired
    StudentDao studentDao;
    @Test
    void contextLoads() {
        System.out.println(studentDao.selectById(8));
    }

    @Test
    void Test1(){
        IPage page = new Page(1,3);
        IPage iPage = studentDao.selectPage(page, null);


    }

}
