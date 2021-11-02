package config;

import com.typefilter.DistrictTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Spring的配置类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Configuration
@ComponentScan(value = "com.itheima",
        excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM,classes = DistrictTypeFilter.class))
public class SpringConfiguration {
}
