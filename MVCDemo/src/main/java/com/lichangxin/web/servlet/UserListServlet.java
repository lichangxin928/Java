package com.lichangxin.web.servlet;

import com.lichangxin.domain.User;
import com.lichangxin.service.UserService;
import com.lichangxin.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 调用 UserService 完成查询
        UserService userService =new UserServiceImpl();
        List<User> all = userService.findAll();
        // 2. 将数据存入到request域中
        System.out.println(all);
        request.setAttribute("users",all);
        // 3. 转发到 list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
