package com.lcx.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest01 {

    /**
     * 测试用户认证
     *      认证：用户登录
     *
     *      1. 根据配置文件创建出 SecurityManagerFactory
     *      2. 通过工厂获取 SecurityManager
     *      3. 将 SecurityManager 绑定到当前运行环境
     *      4. 构造 subject
     *      5. 构造 shiro 的登录数据
     *      6. 主体登录
     *
     */

    @Test
    public void testLogin(){
        // 1. 根据配置文件创建出 SecurityManagerFactory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test-1.ini");
        // 2. 通过工厂获取 SecurityManager
        SecurityManager securityManager = factory.getInstance();
        // 3. 将 SecurityManager 绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
        // 4. 构造 subject
        Subject subject = SecurityUtils.getSubject();
        // 5. 构造 shiro 的登录数据
        String username = "zhangsan";
        String password = "123456";
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        // 6. 主体登录
        subject.login(token);
        System.out.println("用户是否登录成功 ：" + subject.isAuthenticated());
        System.out.println(subject.getPrincipal());

    }
}
