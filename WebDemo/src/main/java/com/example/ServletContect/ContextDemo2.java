package com.example.ServletContect;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContextDemo2", value = "/ContextDemo2")
public class ContextDemo2 extends HttpServlet {
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
         *      3. 功能
         *          1. 获取MIME类型
         *              MIME类型：在互联网通信过程中定义的一种文件数据类型
         *                  格式：大类型/小类型  text/html   image/jpg
         *              获取：String getMimeType(String file)
         */
        // 通过 httpServlet 来获取
        ServletContext servletContext = this.getServletContext();

        // 定义文件名称
        String filename = "a.jpg";

        // 获取 MIME 类型
        String mimeType = servletContext.getMimeType(filename);
        System.out.println(mimeType);
    }
}
