package com.example.webdemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class RequestMethods extends HttpServlet {
    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet.....");
    }
    /**
     * GET /day4/demo?name=lcx
     * 1. 获取请求方式
     *      String getMethod()
     * 2. 获取虚拟目录：/day4
     *      String getContextPath()
     * 3. 获取 Servlet 路径
     *      String getServletPath()
     * 4. 获取get 方式请求参数
     *      String getQueryString()
     * 5. 获取请求 uri
     *      String getRequestURI() /day/demo1
     *      StringBuffer getRequestURL()  /http://localhost/day4/demo
     * 6. 获取版本
     *      String getProtocol()
     * 7. 获取客户机的ip地址
     *      String getRemoteAddr()
     *
     * 获取请求头
     *      String getHeader(String name)
     *      Enumeration<String> getHeaderNames() 获取所有请求头名称
     */


}
