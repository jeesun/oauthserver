package com.simon.handler;

import com.simon.exception.CustomOauthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 自定义WebResponseExceptionTranslator实现类
 *
 * @author simon
 * @create 2018-05-31 23:17
 **/

@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        if(e instanceof OAuth2Exception){
            OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOauthException(oAuth2Exception.getMessage()));
        }else if(e instanceof DisabledException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(new CustomOauthException(e.getMessage()));
        }else{
            return ResponseEntity
                    .status(500)
                    .body(new CustomOauthException(e.getMessage()));
        }
    }
}
