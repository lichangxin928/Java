package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 和jdbc操作相关的配置：
 *      JdbcTemplate创建
 *      DataSource创建
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean("jdbcTemplate1")
    public JdbcTemplate createJdbcTemplateOne(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean("jdbcTemplate2")
    public JdbcTemplate createJdbcTemplateTwo(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }



    @Bean("dataSource")
    public DataSource createDataSource(){
        //1.创建数据源对象
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //2.给属性赋值
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //3.返回
        return dataSource;
    }
}
