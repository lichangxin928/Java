package com.itheima.test;

import com.itheima.utils.LogUtil;
import config.SpringConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringComponentScanTest {

    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2.获取对象
//        UserService userService = ac.getBean("myUserService", UserService.class);
        //3.调用方法
//        userService.saveUser();

        //获取对象
//        AccountService accountService = ac.getBean("accountService",AccountService.class);
        //调用方法
//        accountService.deleteAccount();

        //获取对象
        LogUtil logUtil = ac.getBean("mylogUtil",LogUtil.class);
        logUtil.printLog();
    }
}
