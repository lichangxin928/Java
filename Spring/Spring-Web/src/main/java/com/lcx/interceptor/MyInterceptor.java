package com.lcx.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 纯注解开发中，自定义拦截器的步骤
 *  1. 编写一个普通类，实现HandlerInterceptor 接口
 *  2. 吧拦截器用注解存入 IOC 容器中
 *  3. 注册到 InterceptorRegistry 中
 *
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 拦截器的拦截方法，在控制器方法执行前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         Object obj = request.getSession().getAttribute("loginName");
         if (obj == null) return false;
         return true;
    }

    /**
     * 拦截器后处理方法，在控制器方法执行之后，同时结构视图执行之前，可以对响应数据进行增强
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 拦截器最后执行方法， 在结果视图执行之后，响应之前
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
