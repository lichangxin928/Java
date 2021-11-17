package com.lcx.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan(value = "com.lcx",excludeFilters =
@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class))
@Import(JdbcConfig.class)
@MapperScan(basePackages = "com.lcx.dao")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfig {

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
//
//    @Bean("mybatisSqlSessionFactory")
//    public MybatisSqlSessionFactoryBean MybatisSqlSessionFactoryBean(DataSource dataSource){
//        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean;
//    }
}
