package com.lcx.pa1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

public class Student implements BeanFactoryAware, BeanPostProcessor {
    private String name;
    private Integer age;

    private BeanFactory beanFactory;

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("jjjjjj");
        return bean;
    }
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void doSome() {
        System.out.println("do some......");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
