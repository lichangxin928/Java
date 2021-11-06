package com.lcx.filters.CrossOriginFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossOriginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            System.out.println("解决跨域问题的解决了");
            // 设置 response的响应消息头实现跨域问题
            // 允许跨域的主机地址
            response.setHeader("Access-Control-Allow-Origin","*");
            // 允许跨域的请求方法
            response.setHeader("Access-Control-Allow-Methods","*");
            // 重新预检验跨域的时间
            response.setHeader("Access-Control-Max-Age","3600");
            // 允许跨域的请求头
            response.setHeader("Access-Control-Allow-Headers","*");
            // 是否携带 cookie
            response.setHeader("Access-Control-Allow-Credentials","true");
            // 放行
            filterChain.doFilter(request,response);
        }catch (Exception e){

        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
