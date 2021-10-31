package config;

import com.itheima.condition.LinuxCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 数据库连接的配置类
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
     * 创建windows系统测试时的数据源
     * @return
     */
    @Bean("dataSource")
//    @Conditional(WindowsCondition.class)
    public DataSource createWindowsDataSource(){
        //1.创建对象
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //2.设置属性
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //输出
        System.out.println("Windows URL is:"+url);
        //3.返回
        return dataSource;
    }

    /**
     * 创建Linux版本测试的数据源
     * @param linxuDriver
     * @param linuxUrl
     * @param linuxUsername
     * @param linuxPassword
     * @return
     */
    @Bean("dataSource")
    @Conditional(LinuxCondition.class)
    public DataSource createLinuxDataSource(@Value("${linux.driver}") String linxuDriver,
                                            @Value("${linux.url}") String linuxUrl,
                                            @Value("${linux.username}") String linuxUsername,
                                            @Value("${linux.password}") String linuxPassword){
        //1.创建对象
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //2.设置属性
        dataSource.setDriverClassName(linxuDriver);
        dataSource.setUrl(linuxUrl);
        dataSource.setUsername(linuxUsername);
        dataSource.setPassword(linuxPassword);
        //输出url
        System.out.println("linux URL is："+linuxUrl);
        //3.返回
        return dataSource;
    }

}
