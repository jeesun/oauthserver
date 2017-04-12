package com.simon.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


/**
 * Created by simon on 2017/2/20.
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    public DruidDataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setMaxActive(20);
        dataSource.setInitialSize(1);
        dataSource.setMaxWait(60000);
        dataSource.setMinIdle(3);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(180);
        dataSource.setConnectionProperties("clientEncoding=UTF-8");
        dataSource.setTestWhileIdle(false);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
