package com.simon.common.plugins.jwt;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义TokenEnhancer
 *
 * @author simon
 * @date 2018-12-05
 **/
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken){
            DefaultOAuth2AccessToken token= (DefaultOAuth2AccessToken) oAuth2AccessToken;
            Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
            additionalInformation.put("username",oAuth2Authentication.getName());
            token.setAdditionalInformation(additionalInformation);
        }
        log.info(JSON.toJSONString(oAuth2AccessToken));
        return oAuth2AccessToken;
    }
}
