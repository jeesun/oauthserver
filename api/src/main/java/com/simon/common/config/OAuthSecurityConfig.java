package com.simon.common.config;

import com.simon.common.plugins.oauth.IntegrationAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by simon on 2017/2/18.
 */
@Configuration
@EnableAuthorizationServer
public class OAuthSecurityConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 认证方式
     */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private WebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientIdPassword")
                .redirectUris("http://www.baidu.com")
                .secret("$2a$11$uBcjOC6qWFpxkQJtPyMhPOweH.8gP3Ig1mt27mGDpBncR7gErOuF6") //明文secret
                .scopes("read,write,trust")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password", "client_credentials")
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .accessTokenValiditySeconds(7200)//access_token有效期为2小时
                .refreshTokenValiditySeconds(5184000)//refresh_token有效期为2个月60天
                .autoApprove(false);
        //clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .exceptionTranslator(customWebResponseExceptionTranslator)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                //client password加密即oauth_client_details表的client_secret字段
                .passwordEncoder(passwordEncoder())
        .addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
        //return new RedisTokenStore(connectionFactory);
    }

    @Autowired
    private AuthorizationEndpoint authorizationEndpoint;

    @PostConstruct
    public void init(){
        authorizationEndpoint.setUserApprovalPage("forward:/oauth/my_approval_page");
        authorizationEndpoint.setErrorPage("forward:/oauth/my_error_page");
    }
}
