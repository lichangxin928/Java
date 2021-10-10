package com.example.ServletContect;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ContextDemo1", value = "/ContextDemo1")
public class ContextDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * ServletContext 对象
         *      1. 概念：代表整个web应用，可以和程序的容器（服务器）来通信
         *      2. 获取：
         *          1.通过 request 对象获取
         *              request.getServletContext();
         *          2.通过 httpServlet 来获取
         */
        // 1. 通过 request 来获取
        ServletContext servletContext = request.getServletContext();
        // 2 .通过 httpServlet 来获取
        ServletContext servletContext1 = this.getServletContext();

        System.out.println(servletContext1 == servletContext); // true
    }
}
