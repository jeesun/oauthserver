package com.simon.common.config;

//import com.github.jeesun.thymeleaf.extras.dialect.DbDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * 自定义标签配置
 *
 * @author simon
 * @date 2018-09-16
 **/

@Configuration
public class CustomDialectConfig {
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
