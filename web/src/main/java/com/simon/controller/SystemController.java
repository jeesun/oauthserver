package com.simon.controller;

import com.simon.common.domain.ResultMsg;
import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 系统配置
 *
 * @author simon
 * @date 2018-11-06
 **/
@ApiIgnore
@Api(description = "系统配置")
@Controller
public class SystemController {
    @GetMapping("locale")
    @ResponseBody
    public ResultMsg changeSessionLanguage(
            HttpServletRequest request,
            HttpServletResponse response,
            String lang){
        //获取当前使用的区域解析器LocaleResolver 调用里面的方法setLocale设置即可，这样的代码就是不管是会话还是cookie区域解析器都是一样的代码了。
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if ("zh".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        }else if("en".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        return ResultMsg.success();
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout&locale=" + locale.toString();
    }
}
