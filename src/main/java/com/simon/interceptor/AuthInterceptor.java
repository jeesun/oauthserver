package com.simon.interceptor;

import com.alibaba.fastjson.JSON;
import com.simon.annotation.IgnoreSecurity;
import com.simon.domain.UserInfo;
import com.simon.domain.jdbc.OauthUser;
import com.simon.repository.OauthUserRepository;
import com.simon.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

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
    private UserInfoRepository userInfoRepository;

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
            throw new InvalidTokenException("无效的token");
        }
        token = token.substring(token.indexOf("Bearer ") + "Bearer ".length());
        log.info("token = " + token);
        String username = getUsernameByAccessToken(token);
        OauthUser oauthUser = oauthUserRepository.findByUsername(username);
        UserInfo userInfo = userInfoRepository.findByUserId(oauthUser.getId());
        log.info(JSON.toJSONString(userInfo));
        request.setAttribute("currentUser", userInfo);
        return true;
    }

    private String getUsernameByAccessToken(String access_token){
        return jdbcTemplate.queryForObject("SELECT user_name FROM oauth_access_token" +
                " WHERE encode(token, 'escape') LIKE CONCAT('%', ?)", new Object[]{access_token}, String.class);
        /*return jdbcTemplate.queryForObject("SELECT user_name FROM oauth_access_token" +
                " WHERE right(cast(token as char), 36)=?", new Object[]{access_token}, String.class);*/
    }
}
