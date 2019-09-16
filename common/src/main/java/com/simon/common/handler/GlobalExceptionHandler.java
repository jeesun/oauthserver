package com.simon.common.handler;

import com.simon.common.domain.ResultMsg;
import com.simon.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
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
 * @date 2018-04-25 23:25
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ResultMsg> grantAccessDeniedError(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultMsg.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ResultMsg> grantBadCredentialsError(HttpServletRequest request, HttpServletResponse response, BadCredentialsException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultMsg.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
    }

    @ExceptionHandler(value = {OAuth2Exception.class, InvalidGrantException.class})
    public ResponseEntity<ResultMsg> grantOAuth2Error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultMsg.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ResultMsg> grantBusinessError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultMsg.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResultMsg> grantRuntimeError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultMsg.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
}
