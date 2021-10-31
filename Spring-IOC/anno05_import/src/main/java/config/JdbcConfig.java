package config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 和连接数据库相关的配置
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class JdbcConfig {

    /**
     * 创建数据源
     * @return
     */
    @Bean("dataSource")
    public DataSource createDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_ioc");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
}
