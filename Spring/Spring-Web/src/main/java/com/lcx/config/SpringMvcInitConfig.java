package com.lcx.config;

import com.lcx.filters.CrossOriginFilter.CrossOriginFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

import static javax.servlet.DispatcherType.INCLUDE;

public class SpringMvcInitConfig extends AbstractDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 触发父类的 onStartup
        super.onStartup(servletContext);
        // 创建字符集过滤器对象
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        // 设置使用的字符集
        characterEncodingFilter.setEncoding("UTF-8");
        // 添加到容器中（不是ioc 容器，而是 ServletContainer）
        FilterRegistration.Dynamic registration = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        // 添加映射
        registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,
                DispatcherType.FORWARD,
                INCLUDE),false,"/");

        // 解决跨域问题的过滤器
        FilterRegistration.Dynamic registration1 = servletContext.addFilter("crossOriginFilter", new CrossOriginFilter());
        registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,
                DispatcherType.FORWARD,
                INCLUDE),false,"/");
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        /**
         * 创建 SpringMVC 容器
         */
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(SpringMvcConfiguration.class);
        return annotationConfigWebApplicationContext;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 创建 Spring 容器
     * @return
     */
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(SpringConfiguration.class);
        return applicationContext;
    }

}
