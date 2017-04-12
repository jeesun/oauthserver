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
        dataSource.setMaxActive(env.getProperty("spring.datasource.druid.max-active", Integer.class));
        dataSource.setInitialSize(env.getProperty("spring.datasource.druid.initial-size", Integer.class));
        dataSource.setMaxWait(env.getProperty("spring.datasource.druid.max-wait", Integer.class));
        dataSource.setMinIdle(env.getProperty("spring.datasource.druid.min-idle", Integer.class));
        dataSource.setRemoveAbandoned(env.getProperty("spring.datasource.druid.remove-abandoned", Boolean.class));
        dataSource.setRemoveAbandonedTimeout(env.getProperty("spring.datasource.druid.remove-abandoned-timeout", Integer.class));
        dataSource.setConnectionProperties(env.getProperty("spring.datasource.druid.connection-properties"));
        dataSource.setTestWhileIdle(env.getProperty("spring.datasource.druid.test-while-idle", Boolean.class));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
