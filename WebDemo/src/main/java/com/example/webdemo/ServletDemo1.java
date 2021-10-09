package com.example.webdemo;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet 是单例模式，会存在线程安全问题，不能在其中修改局部变量
 */

public class ServletDemo1 implements Servlet {
    /**
     * 在servlet 被创建时执行，只执行一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    /**
     * 获取 ServletConfig 对象
     * ServletConfig：Servlet 的配置对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务的方法，每一次 servlet 被访问时，执行多次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello servlet");
    }

    /**
     * 获取 Servlet 的信息例如版本
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 在服务器正常关闭时执行，只执行一次
     */
    @Override
    public void destroy() {

    }
}
