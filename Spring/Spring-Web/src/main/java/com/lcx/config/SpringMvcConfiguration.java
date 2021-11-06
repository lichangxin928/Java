package com.lcx.config;

import com.lcx.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;

/**
 * 用于配置控制器
 * @Controller
 * @RestController
 * 用于提供方法映射的
 * @RequestMapping
 * @GetMapping
 * @PostMapping
 * @PutMapping
 * @DeleteMapping
 * @PatchMapping
 * 用于增强控制器方法的
 * @ModeAttribute
 *      处理参数，每次处理请求都先执行这个注解修饰的方法
 * @ExceptionHandler
 * @InitBinder
 * 用于给控制器方法提供通知的注解
 * @ControllerAdvice
 * @RestControllerAdive
 * 用于绑定控制器方法参数的注解
 * @RequestParam
 * @RequestBody
 * @RequestHeader
 * @CookieValue
 * @PathVariable
 * @SessionAttribute
 *      存取数据到会话域中
 * @RequestBody
 * @SessionAttributes
 *
 */
@Configuration()
@ComponentScan("com.lcx.controller")
@EnableWebMvc
public class SpringMvcConfiguration implements WebMvcConfigurer {
    @Resource
    private MyInterceptor myInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/js/**","/images/**","/css/**")
                .addResourceLocations("/js/","/images/","/css/")
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }
    /**
     * 创建视图解析器
     * @return
     */
    @Bean
    public ViewResolver createViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
