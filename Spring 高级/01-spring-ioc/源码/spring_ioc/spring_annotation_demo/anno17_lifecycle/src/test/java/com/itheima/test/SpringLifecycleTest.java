package com.itheima.test;

import com.itheima.utils.LogUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringLifecycleTest {

    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取对象
        LogUtil logUtil = ac.getBean("logUtil", LogUtil.class);
        //3.输出
        System.out.println(logUtil);

        //再次获取对象
        LogUtil logUtil2 = ac.getBean("logUtil", LogUtil.class);

        System.out.println(logUtil == logUtil2);
        //4.关闭容器
        ac.close();
    }
}
