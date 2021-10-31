package com.itheima.test;

import com.itheima.service.UserService;
import com.itheima.utils.LogUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringImportBeanDefinitionRegistrarTest {

    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取对象
        UserService userService = ac.getBean("userServiceImpl", UserService.class);
        //3.执行方法
        userService.saveUser();

        //获取对象
        LogUtil logUtil = ac.getBean("logUtil",LogUtil.class);
        //执行方法
        logUtil.printLog();

    }
}
