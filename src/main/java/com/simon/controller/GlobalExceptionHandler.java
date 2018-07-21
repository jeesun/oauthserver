package com.simon.controller;

import com.simon.domain.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 *
 * @author simon
 * @create 2018-04-25 23:25
 **/
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResultMsg grantAccessDeniedError(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e){
        log.error("grantAccessDeniedError");
        log.error(e.getMessage());
        return ResultMsg.fail(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ExceptionHandler(value = {OAuth2Exception.class})
    public ResultMsg grantOAuth2Error(HttpServletRequest request, HttpServletResponse response, OAuth2Exception e){
        log.error("grantOAuth2Error");
        log.error(e.getMessage());
        return ResultMsg.fail(response.getStatus(), e.getMessage());
    }

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResultMsg grantRuntimeError(HttpServletRequest request, HttpServletResponse response, RuntimeException e){
        log.error("grantRuntimeError");
        log.error(e.getMessage());
        return ResultMsg.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
