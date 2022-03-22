package com.lcx.demo.Service;

import com.lcx.spring.*;

@Component("userService")
@Scope("singleton")
public class UserService implements BeanNameAware, InitializingBean,BeanPostProcessor {

    @Autowired
    private User user;

    private String beanName;

    private String userName;

    public void doSome(){
        System.out.println("do some .....");
    }

    public User getUser() {
        return user;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        this.userName = user.getName();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("初始化前");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后");
        return bean;
    }
}
