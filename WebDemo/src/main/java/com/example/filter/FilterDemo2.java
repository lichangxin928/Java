package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "FilterDemo2",value = "/*")
public class FilterDemo2 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 对 request 进行处理
        System.out.println("来了");
        chain.doFilter(request, response);
        // 放行后面从此处开始执行
        // 对 response 对象进行处理
        System.out.println("回来了");
    }
}
