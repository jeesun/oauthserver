package com.simon.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * 自定义标签配置
 *
 * @author simon
 * @date 2018-09-16
 **/

@Configuration
public class CustomDialectConfig {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CacheManager cacheManager;

    /*@Bean
    public com.jeesun.thymeleaf.extras.dialect.HelloDialect helloDialect(){
        return new com.jeesun.thymeleaf.extras.dialect.HelloDialect(jdbcTemplate);
    }*/

    /*@Bean
    public SpringStandardDialect springStandardDialect(){
        return new SpringStandardDialect();
    }*/
    /**
     * 使Thymeleaf的spring security标签生效
     * 使用办法：html标签内添加xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4"
     * @return
     */
    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}
