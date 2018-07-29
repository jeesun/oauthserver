package com.simon.config;

import com.simon.custom.AuthExceptionEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * Created by simon on 2017/2/25.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers()
                // For org.springframework.security.web.SecurityFilterChain.matches(HttpServletRequest)
                .requestMatchers(
                        new NegatedRequestMatcher(
                                new OrRequestMatcher(
                                        new AntPathRequestMatcher("/login"),
                                        new AntPathRequestMatcher("/logout"),
                                        new AntPathRequestMatcher("/oauth/authorize"),
                                        new AntPathRequestMatcher("/oauth/confirm_access"),
                                        new AntPathRequestMatcher("/oauth/my_approval_page"),
                                        new AntPathRequestMatcher("/oauth/my_error_page")
                                )
                        )
                )
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
