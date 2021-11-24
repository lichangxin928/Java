package com.example.filter;

public class Anote {
    /**
     * Filter: 过滤器
     *  1. 概念
     *      当访问服务器的资源时，过滤器可以将请求拦截下来，完成一些特殊请求
     *      一般用于登录验证，统一编码处理、敏感字符等
     *  2. 步骤
     *      1. 定义一个类，实现 Filter 接口
     *      2. 复写方法
     *      3. 配置拦截路径
     *  3. 过滤器细节
     *      1. web.xml 配置
     *          <filter>
     *              <filter-name>demo1</filter-name>
     *              <filter-class>com.example.filter.FilterDemo1</filter-class>
     *          </filter>
     *          <filter-mapping>
     *              <filter-name>demo1</filter-name>
     *              <!--拦截路径-->
     *              <url-pattern>/*</url-pattern>
     *          </filter-mapping>
     *      2. 过滤器执行流程
     *          来的时候执行放行前的代码，回来的时候执行放行后的代码
     *      3. 过滤器生命周期方法
     *          1. 初始化时，执行 init() 方法 执行一次
     *          2. 请求时执行 Filter方法 执行多次
     *          3. 服务器正常销毁时，执行 destroy 方法 执行一次
     *      4. 过滤器配置详解
     *          拦截路径配置:
     *              1. 具体路径：/index.jsp 只有访问index.jsp
     *              2. 目录拦截：/user/* 访问 /user 下的所有资源时，过滤器都会执行
     *              3. 后缀名拦截：*.jsp 访问所有后缀名时
     *              4. 拦截所有资源：/*
     *          拦截方式配置:资源被访问的方式
     *              注解配置：
     *                  设置 dispatcherType属性
     *                  1. REQUEST ：默认 request
     *                  2. FORWARD：转发访问
     *                  3. INCLUDE：包含访问
     *                  4. ERROR：错误跳转
     *                  5. ASYNC：异步访问
     *
     *      5. 过滤器链（配置多个过滤器）
     *          执行顺序
     *          先后顺序
     *              类名和配置先后
     */
}
