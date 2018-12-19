package com.simon.common.config;

import com.simon.common.handler.AuthSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by simon on 2017/2/18.
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

    /*@Autowired
    private CustomLoginAuthProvider authProvider;*/

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

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder(11));
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //logoutSuccessUrl如果设为/login，那么退出后，
        //会重新自动创建session，触发OnLineCountListener的sessionCreated方法，造成在线人数不准。
        http
                .headers().frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable()
                .and()
                .csrf().disable()
                .formLogin()
                .successHandler(authSuccessHandler)
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .requestMatchers()
                // For org.springframework.security.web.SecurityFilterChain.matches(HttpServletRequest)
                .requestMatchers(
                        new OrRequestMatcher(
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/logout"),
                                new AntPathRequestMatcher("/oauth/authorize"),
                                new AntPathRequestMatcher("/oauth/confirm_access"),
                                new AntPathRequestMatcher("/oauth/my_approval_page"),
                                new AntPathRequestMatcher("/oauth/my_error_page")
                        )
                )
                .and()
                .authorizeRequests()
                .antMatchers("/img/**", "/js/**", "/css/**", "/webjars/**", "/video/**", "/plug-in/**", "/font/**", "/fonts/**", "/json/**", "/fileUpload/**")
                .permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe();

        http
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?result=loginAnotherLocation");

        //logoutSuccessUrl如果设为/login，那么退出后，
        //会重新自动创建session，触发OnLineCountListener的sessionCreated方法，造成在线人数不准。
        /*http
                .headers().frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable()
                .and()
                .formLogin()
                .successHandler(authSuccessHandler)
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/index/index_v1")
                .and()
                .logout().permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .and()
                .csrf().ignoringAntMatchers("/druid/*")
                .and()
                .authorizeRequests()
                .antMatchers("/img/**", "/js/**", "/css/**", "/webjars/**", "/video/**", "/plug-in/**", "/font/**").permitAll()
                .antMatchers("/login", "/logout", "/register", "/register_result", "/forget_password", "/reset_password", "/users/sendEmail", "/users/resetPassword", "/users/forgetPwd","/users/check", "/users/resetPwd", "/users/test", "/users/checkExists").permitAll()
                .antMatchers("/just_test", "/upload", "/users/uuid/**", "/users/loopCheck/**", "/users/register", "/hello").permitAll()
                .anyRequest().permitAll()
                .and()
                .rememberMe()
                .and().csrf().ignoringAntMatchers("/register", "/users/register", "/users/forgetPwd", "/fileUploads/**", "/api/fileUploads/**", "/api/aliPays/**", "/api/wxPays/**");

        //只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
        http
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?result=loginAnotherLocation");*/
    }

}
