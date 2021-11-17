package com.lcx.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //触发父类的onStartup
        super.onStartup(servletContext);
        //1.创建字符集过滤器对象
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        //2.设置使用的字符集
        characterEncodingFilter.setEncoding("UTF-8");
        //3.添加到容器（它不是ioc容器，而是ServletContainer）
        FilterRegistration.Dynamic registration = servletContext.addFilter("characterEncodingFilter",characterEncodingFilter);
        //4.添加映射
        registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE),
                false,"/*");

        //解决跨域的过滤器
//        FilterRegistration.Dynamic registration1 = servletContext.addFilter("crossOriginFilter",new CrossOriginFilter());
//        registration1.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE),
//                false,"/*");
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
