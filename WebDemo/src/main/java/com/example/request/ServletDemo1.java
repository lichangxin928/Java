package com.example.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

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
 *      Enumeration<String> getHeaderNames() 获取所有请求头名称\
 *      referer
 *      user-agent
 *
 * 获取请求体数据
 *      请求体：只有post 请求方式，才有请求体，在请求体中封装了 POST 请求的请求参数
 *      1. 获取流对象
 *          BufferReader getReader() : 获取字符输入流，只能操作字符数据
 *          ServletInputStream getInputSteam() 获取字节输入流，可以操作所有类型数据
 *
 * 其他功能
 *      1. 获取请求参数通用方式
 *          1. String getParameter(String name): 根据参数名获取参数值
 *          2. String getParameterValues(String name): 获取参数数组
 *          3. Enumeration<String> getParameterNames(): 获取所有参数名称
 *          4. Map<String,String[]> getParameterMap(): 获取所有参数的 map 集合
 *
 *      2. 中文乱码问题
 *          1. get: tomcat8 版本以上已经解决
 *          2. post：request.setCharacterEncoding("utf-8"); 在获取参数前设置
 *
 *      3. 请求转发
 *          步骤：
 *              1. 通过 request 对象请求转发器对象 getRequestDispatcher(String path)
 *              2. 使用 RequestDispatcher 对象来进行转发：forward(ServletRequest request,ServletResponse response)
 *          特点：
 *              1. 浏览器地址栏路径不发生变化
 *              2. 只能转发到当前服务器内部资源中
 *              3. 转发是一次请求，多个请求使用的是同一个请求
 *      4. 共享数据：
 *          域对象：一个有作用范围的对象，可以在范围内共享数据
 *          request 代表一次请求的范围，一般用于请求转发的多个资源中共享数据
 *          方法：
 *              1. void setAttribute(String name,Object obj) 存储数据
 *              2. Object getAttribute(String name) 获得
 *              3. void removeAttribute(String name) 移除
 *
 *      5. 获取 ServletContext 对象
 *          request.getServletContext()
 *
 *
 *
 */
@WebServlet(name = "ServletDemo1", value = "/ServletDemo1")
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        System.out.println("请求方式为" + method);
        String contextPath = request.getContextPath();
        System.out.println("虚拟目录为：" + contextPath);
        String servletPath = request.getServletPath();
        System.out.println("Servlet 路径为" + servletPath);
        String queryString = request.getQueryString();
        System.out.println("请求参数为" + queryString);
        String requestURI = request.getRequestURI();
        System.out.println("URI 为" + requestURI);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("URL 为" + requestURL);
        String protocol = request.getProtocol();
        System.out.println("http 版本为" + protocol);
        String remoteAddr = request.getRemoteAddr();
        System.out.println("主机ip为" + remoteAddr);

        // 获取请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement()+ "  --  " + request.getHeader(headerNames.nextElement()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // post 方法
        // 1. 获取字符流
//        BufferedReader reader = request.getReader();
//        // 2. 读取数据
//        String line = null;
//        while((line = reader.readLine()) != null){
//            System.out.println(line);
//        }
        String user = request.getParameter("user");
        System.out.println(user);
        String psw = request.getParameter("psw");
        System.out.println(psw);
    }

}
