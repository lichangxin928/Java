package com.lcx;

import com.lcx.pa1.School;
import com.lcx.pa1.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    /**
     * 依赖注入：表示创建对象，给属性赋值
     *
     * di的实现方法有两种，
     *  一种是在spring的配置文件中，使用标签属性来完成，叫做基于 xml 的实现
     *  第二种是用注解的方式
     *
     * di 是语法分类
     *  1. set注入（设置注入）：spring 调用类的set 方法，在set方法可以实现属性的赋值
     *  2. 构造注入，spring 调用类的有参构造方法，创建对象。
     *
     * 注意事项：
     *  必须要有set方法才能使用，并且只执行set方法，不关心方法的执行过程
     *  并且只是执行这个方法，不一定需要有这个属性
     *  不管是整形还是字符串都必须加上引号
     * 引用类型：property name="name" ref="id"
     *  配置对象不用关心顺序
     *
     */
    @org.junit.Test
    public void Test01(){
       String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        User myUser = (User) ac.getBean("myUser");
        System.out.println(myUser);
        School mySchool = (School) ac.getBean("mySchool");
        System.out.println(mySchool);
        Object myUser2 = ac.getBean("myUser2");
        System.out.println(myUser2);
    }
}
