package config;

import com.itheima.utils.LogUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Configuration
@ComponentScan(value = "com.itheima",excludeFilters = {@ComponentScan.Filter(LogUtil.class)} )
public class SpringConfiguration {
}
