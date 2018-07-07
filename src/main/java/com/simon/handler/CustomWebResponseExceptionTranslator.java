package com.simon.handler;

import com.simon.exception.CustomOauthException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 自定义WebResponseExceptionTranslator实现类
 *
 * @author simon
 * @create 2018-05-31 23:17
 **/

@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    private static Logger logger = Logger.getLogger(CustomWebResponseExceptionTranslator.class);

    @Autowired
    private MessageSource messageSource;

    private Locale locale = LocaleContextHolder.getLocale();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        if(e instanceof InvalidTokenException){
            InvalidTokenException invalidTokenException = (InvalidTokenException) e;
            if("Token was not recognised".equals(invalidTokenException.getMessage())){
                return ResponseEntity
                        .status(invalidTokenException.getHttpErrorCode())
                        .body(new CustomOauthException(messageSource.getMessage("tokenWasNotRecognised", null, locale)));
            }
        }
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
