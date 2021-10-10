package com.example.reponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResponseDemo3", value = "/ResponseDemo3")
public class ResponseDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("GBK");
        // 告诉浏览器，服务器发送的消息是什么格式
//        response.setHeader("content-type","text/html;charset=utf-8");

        // 简单的方法
        response.setContentType("text/html;charset=utf-8");
        // 1. 获取字符输出流
        PrintWriter writer = response.getWriter();
        // 2. 输出数据
        writer.write("你好 <h2>hello world</h2>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
