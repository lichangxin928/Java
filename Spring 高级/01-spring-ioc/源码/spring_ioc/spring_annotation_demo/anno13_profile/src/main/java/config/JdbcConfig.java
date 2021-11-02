package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * jdbc相关的配置
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

    /**
     * 开发环境的数据源
     * @return
     */
    @Bean("dataSource")
    @Profile("dev")
    public DruidDataSource createDevDataSource(){
        //1.创建数据源对象
        DruidDataSource dataSource = new DruidDataSource();
        //2.设置属性
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //3.开发环境的最大活动连接数：5
        dataSource.setMaxActive(5);
        //4.返回
        return dataSource;
    }

    /**
     * 测试环境的数据源
     * @return
     */
    @Bean("dataSource")
    @Profile("test")
    public DruidDataSource createTestDataSource(){
        //1.创建数据源对象
        DruidDataSource dataSource = new DruidDataSource();
        //2.设置属性
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //3.测试环境的最大活动连接数：50
        dataSource.setMaxActive(50);
        //4.返回
        return dataSource;
    }

    /**
     * 生产环境的数据源
     * @return
     */
    @Bean("dataSource")
    @Profile("pro")
    public DruidDataSource createProDataSource(){
        //1.创建数据源对象
        DruidDataSource dataSource = new DruidDataSource();
        //2.设置属性
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //3.生产环境的最大活动连接数：150
        dataSource.setMaxActive(150);
        //4.返回
        return dataSource;
    }


}
