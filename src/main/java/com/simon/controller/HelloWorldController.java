package com.simon.controller;

import com.simon.annotation.CurrentUser;
import com.simon.annotation.IgnoreSecurity;
import com.simon.domain.ResultMsg;
import com.simon.model.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Locale;

/**
 * 测试
 *
 * @author simon
 * @create 2018-05-31 10:10
 **/
@Api("测试")
@RestController
@RequestMapping("/api/helloWorlds")
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

    @ApiOperation("测试仅ADMIN可访问")
    @RolesAllowed("ADMIN")
    @GetMapping(value = "/admin")
    public ResultMsg admin(){
        return ResultMsg.success(200, "你是admin用户");
    }

    @ApiOperation("测试")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResultMsg all(@CurrentUser UserInfo userInfo){
        return ResultMsg.success(200, "", userInfo);
    }

    @ApiOperation("测试（不需要传Authentication）")
    @GetMapping("notAuth")
    public ResultMsg notAuth(){
        return ResultMsg.success(200, "无需登录");
    }

    @IgnoreSecurity
    @ApiOperation(value = "测试国际化，传locale参数，值可取zh_CN, en_US等，例如locale=zh_CN", notes = "忽略安全，不需要传token")
    @GetMapping("testLocale")
    public ResultMsg testLocale(HttpServletRequest request, HttpServletResponse response, Locale locale){
        return ResultMsg.success(200, messageSource.getMessage("helloWorld", null, locale));
    }

    @ApiOperation(value = "测试获取用户名")
    @GetMapping("getUsername")
    public ResultMsg getUserName(Principal principal){
        return ResultMsg.success(200, principal.getName());
    }

    @ApiOperation(value = "测试获取用户名2")
    @GetMapping("currentUserName")
    public ResultMsg currentUserName(Authentication authentication) {
        return ResultMsg.success(200, authentication.getName());
    }

    @ApiOperation(value = "测试获取用户")
    @GetMapping("currentUser")
    public ResultMsg currentUser(Authentication authentication) {
        return ResultMsg.success(200, "", authentication.getPrincipal());
    }
}
