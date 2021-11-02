package com.itheima.tset;

import com.alibaba.druid.pool.DruidDataSource;
import config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类：由于要指定不同的环境，此处使用Junit的注解来指定环境
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
@ActiveProfiles("pro")
public class SpringProfileTest {

    @Autowired
    @Qualifier("dataSource")
    private DruidDataSource druidDataSource;

    @Test
    public void testDataSource(){
        System.out.println(druidDataSource.getMaxActive());
    }
}
