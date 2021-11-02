package config;

import annotations.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Spring的配置类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Configuration
public class SpringConfiguration {

    @Resource(name="dataSource")
    private DataSource dataSource;

    /**
     * 创建一个数据源对象
     * @return
     */
    @Bean(value="dataSource",autowireCandidate = true)
    public DataSource createDataSource(){
        //1.创建数据源对象
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //2.调用初始化方法
        //....
        //3.返回bean对象
        return dataSource;
    }

    /**
     * 创建JdbcTemplate对象
     * @return
     */
    @MyBean
    public JdbcTemplate createJdbcTemplate(){
        System.out.println("执行了没有参数的createJdbcTemplate");
        return new JdbcTemplate(dataSource);
    }

//    @Bean//(value="jdbcTemplate")
//    public JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource){
//        System.out.println("创建了JdbcTemplate");
//        return new JdbcTemplate(dataSource);
//    }

}
