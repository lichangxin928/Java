package com.example.reponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * 完成重定向
 *
 */
@WebServlet(name = "ResponseDemo1", value = "/ResponseDemo1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置状态码
//        response.setStatus(302);
//        response.setHeader("location","/WebDemo_war_exploded/ResponseDemo2");
        // 简单版本
        response.sendRedirect("/WebDemo_war_exploded/ResponseDemo2");
        System.out.println("demo1,,,");
    }
}
