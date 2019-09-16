package com.simon.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceServerConfigurerAdapter是默认情况下spring security oauth2的http配置
 * @author simon
 * @date 2017/2/25
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .headers().frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().loginPage("/login").permitAll();
    }
}
