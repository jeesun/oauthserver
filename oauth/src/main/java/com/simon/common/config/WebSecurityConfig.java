package com.simon.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author simon
 * @date 2017/2/18
 */
@Slf4j
//WebSecurityConfigurerAdapter的配置的拦截要优先于ResourceServerConfigurerAdapter
@Configuration
@EnableWebSecurity
//Spring Security默认是禁用注解的，要想开启注解，需要在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解，来判断用户对某个控制层的方法是否具有访问权限
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html","/v2/api-docs").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/users/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .httpBasic().disable()
                .csrf().disable();
    }

}
