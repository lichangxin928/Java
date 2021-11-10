package com.lcx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@ComponentScan(value = "com.lcx",includeFilters =
        {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})
public class WebConfig implements WebMvcConfigurer {
    // 配置视图解析器

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");

        registry.viewResolver(resolver);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/js/**","/images/**","/css/**")
                .addResourceLocations("/js/","/images/","/css/")
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }
}
