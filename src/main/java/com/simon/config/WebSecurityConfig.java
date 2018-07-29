package com.simon.config;

import com.simon.custom.CustomLoginAuthProvider;
import com.simon.handler.AuthSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by simon on 2017/2/18.
 */
@Slf4j
@Order(2)
//WebSecurityConfigurerAdapter的配置的拦截要优先于ResourceServerConfigurerAdapter
@Configuration
//Spring Security默认是禁用注解的，要想开启注解，需要在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解，来判断用户对某个控制层的方法是否具有访问权限
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomLoginAuthProvider authProvider;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hystrix.stream*//**", "/info", "/error");
     }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder(11));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers()
                // For org.springframework.security.web.SecurityFilterChain.matches(HttpServletRequest)
                .requestMatchers(
                        new OrRequestMatcher(
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/logout"),
                                new AntPathRequestMatcher("/oauth/authorize"),
                                new AntPathRequestMatcher("/oauth/confirm_access")
                        )
                )
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }
}
