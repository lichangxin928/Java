package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * spring的配置类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Configuration
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbc.xml")
//@PropertySource("classpath:jdbc.properties")
//@PropertySource("file:///I:/spring_ioc/spring_annotation_demo/anno08_propertysource/src/main/resources/jdbc.properties")
public class SpringConfiguration {


    //针对Spring4.3版本之前的properties文件解析器，在4.3版本之后使用PropertySourceFactory接口的唯一实现类：DefaultPropertySourceFactory
    //注册资源文件解析器的bean到ioc容器中
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer(){
//        return new PropertySourcesPlaceholderConfigurer();
//    }

}
