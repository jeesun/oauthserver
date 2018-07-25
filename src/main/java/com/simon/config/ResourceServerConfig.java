package com.simon.config;

import com.simon.custom.AuthExceptionEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * Created by simon on 2017/2/25.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //antMathcers先定义的优先级和高
        http
                .formLogin().permitAll()
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html","/v2/api-docs").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/**").permitAll()
                //.antMatchers("/**").authenticated() will
                // cause the url "http://localhost:8080/swagger-ui.html" be blank html,
                //just remaining the head.
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }
}
