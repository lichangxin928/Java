package com.lcx.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

public class ShiroTest03 {

    private SecurityManager securityManager;

    @Before
    public void init(){
        // 1. 根据配置文件创建出 SecurityManagerFactory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test-2.ini");
        // 2. 通过工厂获取 SecurityManager
        securityManager = factory.getInstance();
        // 3. 将 SecurityManager 绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
    }


    @Test
    public void testLogin(){
        // 登录
        Subject subject = SecurityUtils.getSubject();
        String username = "zhangsan";
        String password = "123456";
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);

        // 登录成功后，完成授权
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:save"));

    }
}
