package com.itheima.test;

import config.JdbcConfig;
import config.SpringConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringImportTest {

    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2.获取对象
//        DataSource dataSource = ac.getBean("dataSource",DataSource.class);
        //3.输出对象
//        System.out.println(dataSource);

        JdbcConfig jdbcConfig = ac.getBean("config.JdbcConfig",JdbcConfig.class);
        System.out.println(jdbcConfig);

        //1.获取容器中所有bean的唯一标识
//        String[] names = ac.getBeanDefinitionNames();
        //2.遍历数组，取出每个bean的唯一标识
//        for(String name : names){
//            System.out.println(name);
//        }
    }
}
