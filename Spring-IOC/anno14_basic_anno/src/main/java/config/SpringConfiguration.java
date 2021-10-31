package config;

import org.springframework.context.annotation.*;
import support.factory.YmlPropertySourceFactory;

/**
 * spring的配置类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Configuration
@ComponentScan("com.itheima")
@Import(JdbcConfig.class)
@PropertySource(value = "classpath:jdbc.yml",factory = YmlPropertySourceFactory.class)
public class SpringConfiguration{
}
