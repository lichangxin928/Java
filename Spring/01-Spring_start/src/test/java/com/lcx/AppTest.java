package com.lcx;

import static org.junit.Assert.assertTrue;

import com.lcx.pa1.Config;
import com.lcx.pa1.SomeService;
import com.lcx.pa1.SomeServiceImpl;
import com.lcx.pa1.Student;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * 传统方式创建对象
     */
    @Test
    public void Test1(){
        // 传统方法
        SomeService service = new SomeServiceImpl();
        service.doSome();
    }

    /**
     * spring 默认创建容器的时间是在 创建容器时创建所有对象
     * 默认方式是调用无参数的构造方法
     */
    @Test
    public void Test2(){
        // IOC
        // 1，指定 spring 配置文件名称
        String config = "spring_config.xml";
        // 2. 创建表示spring 容器的对象。 applicationContext
        // ApplicationContext 就是表示 spring 的容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
        Student someService = (Student) applicationContext.getBean("someService");
        someService.doSome();
        BeanFactory beanFactory = someService.getBeanFactory();
        System.out.println(beanFactory);
    }

    /**
     * 获取spring 中的java对象信息
     */
    @Test
    public void Test3(){
        // 1，指定 spring 配置文件名称
        String config = "spring_config.xml";
        // 2. 创建表示spring 容器的对象。 applicationContext
        // ApplicationContext 就是表示 spring 的容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
        // 获取定义对象的数量
        int count = applicationContext.getBeanDefinitionCount();
        System.out.println("定义的数量为：" + count);
        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
    }

    /**
     * spring 创建一个非自定义的类
     */
    @Test
    public void Test4(){
        // 定义配置文件名称
        String config = "spring_config.xml";
        // 创建 ApplicationContext
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        // 通过 ac 来获取对象
        Date date = (Date) ac.getBean("date");
        System.out.println(date);
    }

    /**
     * 给java对象属性赋值
     */


    static class Variable{
        static int a = 1;
        static boolean flag = false;
    }

    public static void main(String []args){

        new Thread(()->{
            Variable.a ++;
            Variable.flag = true;

        }).start();
        new Thread(()->{
            if (Variable.flag){
                System.out.println(Variable.a);
            }
        }).start();

    }


    @Test
    public void ThreeCache(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    }
}
