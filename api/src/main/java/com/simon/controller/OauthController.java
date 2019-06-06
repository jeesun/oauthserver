package com.simon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.HtmlUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义Oauth授权页面
 *
 * @author simon
 * @create 2018-07-28 15:04
 **/
@ApiIgnore
@Slf4j
@Controller
@SessionAttributes({"authorizationRequest"})
public class OauthController {
    @RequestMapping("/oauth/my_approval_page")
    public String oauthAuthorize(Map<String, Object> model, HttpServletRequest request){
        log.info("oauthAuthorize");
        @SuppressWarnings("unchecked")
        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
        Map<String, String> resultMap = new LinkedHashMap<>();
        resultMap.putAll(scopes);
        for (Map.Entry<String, String> entry : scopes.entrySet()) {
            resultMap.put("scopes", entry.getKey());
        }
        resultMap.put("client_id", String.valueOf(request.getAttribute("client_id")));
        model.putAll(resultMap);
        return "oauth_approval";
    }

    @RequestMapping("/oauth/my_error_page")
    public String oauthError(Map<String, Object> model, HttpServletRequest request){
        Object error = request.getAttribute("error");
        String errorSummary;
        if(error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;
            errorSummary = HtmlUtils.htmlEscape(oauthError.getSummary());
            log.error(oauthError.getMessage());
        } else {
            errorSummary = "Unknown error";
        }
        model.put("errorSummary", errorSummary);
        return "oauth_error";
    }
}
