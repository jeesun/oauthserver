package com.simon.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        resultMap.put("client_id", String.valueOf(request.getAttribute("client_id")));

        log.info(JSON.toJSONString(resultMap));
        model.putAll(resultMap);
        return "oauth_approval";
    }
}
