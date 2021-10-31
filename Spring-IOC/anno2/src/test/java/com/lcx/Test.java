package com.lcx;

import com.lcx.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    /**
     * 测试 Configuration注解
     * @param args
     */
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.lcx.config");
        //不一定非要加 Configuration 注解，能够通过字节码文件来创建                                                                                                                                                                                                      二十四a
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        SpringConfig bean = ac.getBean(SpringConfig.class);
        System.out.println(bean);
    }
}
