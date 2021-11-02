package com.itheima.test;

import com.itheima.service.DistrictPercentage;
import com.itheima.service.DistrictPerformance;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class SpringFilterTest {

    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取对象
        DistrictPercentage districtPercentage = ac.getBean("districtPercentage",DistrictPercentage.class);
        //3.执行方法
        districtPercentage.salePercentage("SUV");


        //获取对象
        DistrictPerformance districtPerformance = ac.getBean("districtPerformance",DistrictPerformance.class);
        //执行方法
        districtPerformance.calcPerformance("SUV");
    }
}

