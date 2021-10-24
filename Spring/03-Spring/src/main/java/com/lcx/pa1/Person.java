package com.lcx.pa1;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Component:创建对象，等同于<bean> 的功能
 *      属性：value 就是对象的名称，也就是 bean 的id值
 *           value的值是唯一的，在整个spring 容器中是唯一的
 *      位置：类的上方
 *
 * 和 @Component() 功能一致，创建对象的注解还有：
 *  1. @Repository 用在 dao 层的
 *  2. @Service 用在 service 层的
 *  3. @controller 用在控制器类上的，创建控制器对象的
 *              控制器对象，能够接受用户提交的参数，显示请求的处理结果
 *  以上三个组件的使用和@component 一样。都能创建对象，但是这个三个组件还有额外的功能
 *  用处是给项目分层，除了 这三个注解的类，用使用 Component
 *
 *
 * 简单类型的属性赋值
 * @Value()
 *  属性:value 是 String 类型的，表示简单类型的属性值
 *  位置：1. 在属性定义的上面，无需 set 方法，推荐使用
 *       2. 在 set 方法上
 *
 * 引用类型赋值
 * @Autowired：spring 框架提供的注解，实现引用类型的赋值
 *  spring 总通过注解给引用类型赋值，使用的就是自动注入原理，支持 byName，byType
 * @Autowire的：默认使用的是byType 自动注入
 *
 * 位置：1. 在属性定义的上面，无需 set 方法，推荐使用
 *      2. 在set 方法的上面
 *
 * require=true
 * 表示引用类型，如果赋值失败，程序报错
 * 当 require=false 时，程序正常运行，值为 null
 *
 *  如果要使用 byName 的方式
 *  用到两个注解一起
 *  1. 在属性上面加入 @Autowired
 *  2. 在属性上面加入 @Qualifier(value="bean 的 id")：表示指定名称的 bean 完成赋值
 *
 * @Resource 来着jdk的注解，spring 提供了对这个注解的功能支持，可以给使用它给引用类型赋值
 *              使用的也是自动注入原理，支持byName byType 默认是byName
 *           位置：
 *              1. 在属性定义的上面，无需set 方法，推荐使用
 *              2. 在set 方法的上面
 *
 *           先使用 byName 再使用 byType
 *           只使用 byName 的方式，就使用 name = "beanId"
 */

@Component("myPerson")
public class Person {

    @Value("zhangasn")
    private String name;
    @Value("123")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
