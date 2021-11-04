package com.lcx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class JdbcConfiguration {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.userName}")
    private String userName;
    @Value("${jdbc.psw}")
    private String psw;

    @Bean("dataSource")
    DataSource createDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(psw);
        return ds;
    }
    @Bean("jdbcTemplate")
    JdbcTemplate getJdbcTemplate(@Autowired DataSource ds){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        return jdbcTemplate;
    }
}
