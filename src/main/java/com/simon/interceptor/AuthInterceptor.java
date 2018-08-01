package com.simon.interceptor;

import com.alibaba.fastjson.JSON;
import com.simon.annotation.IgnoreSecurity;
import com.simon.config.AppConfig;
import com.simon.model.OauthUser;
import com.simon.repository.OauthUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Auth拦截器读取token向request添加CurrentUser
 *
 * @author simon
 * @create 2018-07-22 10:26
 **/
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OauthUserRepository oauthUserRepository;

    @Autowired
    private MessageSource messageSource;

    private Locale locale = AppConfig.getLocale();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接跳过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        String requestPath = request.getRequestURI();

        if(requestPath.contains("/v2/api-docs") || requestPath.contains("swagger") || requestPath.contains("/configuration/ui")){
            return true;
        }
        if(requestPath.contains("/error")){
            return true;
        }
        if(method.isAnnotationPresent(IgnoreSecurity.class)){
            return true;
        }
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
            log.error(messageSource.getMessage("invalidToken", null, locale));
            throw new InvalidTokenException(messageSource.getMessage("invalidToken", null, locale));
        }
        token = token.substring(token.indexOf("Bearer ") + "Bearer ".length());
        log.info("token = " + token);
        String username = getUsernameByAccessToken(token);
        log.info("username = " + username);
        OauthUser oauthUser = oauthUserRepository.findByUsername(username);
        log.info("userInfo = " + JSON.toJSONString(oauthUser));
        request.setAttribute("currentUser", oauthUser);
        return true;
    }

    private String getUsernameByAccessToken(String access_token){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
        /*return jdbcTemplate.queryForObject("SELECT user_name FROM oauth_access_token" +
                " WHERE encode(token, 'escape') LIKE CONCAT('%', ?)", new Object[]{access_token}, String.class);*/
        /*return jdbcTemplate.queryForObject("SELECT user_name FROM oauth_access_token" +
                " WHERE right(cast(token as char), 36)=?", new Object[]{access_token}, String.class);*/
    }
}
