package com.lcx.pa1;


import org.springframework.stereotype.Component;

/**
 * @Component:创建对象，等同于<bean> 的功能
 *      属性：value 就是对象的名称，也就是 bean 的id值
 *           value的值是唯一的，在整个spring 容器中是唯一的
 *      位置：类的上方
 */

@Component("myPerson")
public class Person {
    private String name;
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
