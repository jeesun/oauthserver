package com.simon.common.config;

import com.simon.common.handler.AuthSuccessHandler;
import com.simon.common.handler.CustomAuthenticationFailureHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;
import java.net.URLEncoder;

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
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //logoutSuccessUrl如果设为/login，那么退出后，
        //会重新自动创建session，触发OnLineCountListener的sessionCreated方法，造成在线人数不准。
        http
                .headers().frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable()
                .and()
                .formLogin()
                .successHandler(authSuccessHandler)
                .loginPage("/login").permitAll()
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .logout().permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .and()
                .csrf().ignoringAntMatchers("/druid/*", "/register", "/users/register", "/users/forgetPwd", "/fileUploads/**", "/api/fileUploads/**", "/api/aliPays/**", "/api/wxPays/**", "/api/oauthUsers/import", "/info")
                .and()
                .authorizeRequests()
                .antMatchers("/img/**", "/js/**", "/css/**", "/webjars/**", "/video/**", "/plug-in/**", "/font/**", "/info").permitAll()
                .antMatchers("/login", "/logout", "/register", "/register_result", "/forget_password", "/reset_password", "/users/sendEmail", "/users/resetPassword", "/users/forgetPwd","/users/check", "/users/resetPwd", "/users/test", "/users/checkExists", "/api/oauthUsers/getVeriCode", "/api/oauthUsers/checkVeriCode").permitAll()
                .antMatchers("/just_test", "/upload", "/users/uuid/**", "/users/loopCheck/**", "/users/register", "/hello", "/api/demos/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe();

        //只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
        http
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?errMsg=" + URLEncoder.encode("您已在其他地方登录", "utf-8"));
    }
}
