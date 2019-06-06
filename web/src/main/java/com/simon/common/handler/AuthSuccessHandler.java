package com.simon.common.handler;

import com.simon.common.domain.UserEntity;
import com.simon.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录处理
 *
 * @author simon
 * @create 2018-07-27 1:30
 **/

@Slf4j
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static Logger logger = Logger.getLogger(AuthSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserEntity userDetails = (UserEntity) authentication.getPrincipal();

        logger.info("authentication.isAuthenticated()=" + authentication.isAuthenticated());

        HttpSession httpSession = request.getSession();
        logger.info("session create time = " + DateUtil.format(httpSession.getCreationTime(), "yyyy-MM-dd HH:mm:ss"));

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

