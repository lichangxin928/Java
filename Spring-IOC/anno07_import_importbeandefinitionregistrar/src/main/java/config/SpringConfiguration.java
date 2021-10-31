package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import registrar.CustomeImportBeanDefinitionRegistrar;

/**
 * spring的配置类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Configuration
@ComponentScan("com.itheima.service")
@Import(CustomeImportBeanDefinitionRegistrar.class)
public class SpringConfiguration {
}
