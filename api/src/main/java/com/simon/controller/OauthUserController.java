package com.simon.controller;

import com.simon.common.config.AppConfig;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.common.domain.UserEntity;
import com.simon.common.exception.RegisterException;
import com.simon.common.factory.SmsServiceFactory;
import com.simon.common.utils.BeanUtils;
import com.simon.model.OauthUser;
import com.simon.service.BaseSmsService;
import com.simon.service.OauthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

/**
 * @author simon
 * @date 2016/8/21
 */
@Slf4j
@Api(description = "用户信息")
@Controller
@RequestMapping("/api/oauthUsers")
public class OauthUserController extends BaseController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("获取用户信息")
    @GetMapping("/userInfo")
    @ResponseBody
    public ResultMsg getUserInfo(Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        if (null != userEntity) {
            return ResultMsg.success(userEntity);
        }
        return ResultMsg.fail(ResultCode.FAIL);
    }

    @ApiOperation(value = "注册：手机号+验证码")
    @PermitAll
    @PostMapping("/register/phoneAndCode")
    @ResponseBody
    public ResultMsg register(
            @ApiParam(value = "区号", required = true, defaultValue = "+86") @RequestParam String areaCode,
            @ApiParam(value = "手机号", required = true) @RequestParam String phone,
            @ApiParam(value = "验证码", required = true) @RequestParam String code) {
        BaseSmsService smsService = SmsServiceFactory.getInstance().getSmsService(AppConfig.SMS_SERVICE_IMPL);
        if (smsService.checkCode(phone, code)) {
            //手机号验证码正确，注册账号
            OauthUser oauthUser = oauthUserService.registerByPhone(areaCode, phone);
            if (null == oauthUser) {
                throw new RegisterException("注册失败，请稍后重试");
            }
            return ResultMsg.success();
        } else {
            return ResultMsg.fail(ResultCode.ERROR_VERI_CODE);
        }
    }

    @ApiOperation(value = "注册: (手机号、邮箱、用户名)+密码")
    @PermitAll
    @PostMapping("/register/accountAndCode")
    @ResponseBody
    public ResultMsg register(
            @ApiParam(value = "账号（手机号、邮箱、用户名）", required = true) @RequestParam String account,
            @ApiParam(value = "密码", required = true) @RequestParam String password) {
        return ResultMsg.success();
    }

    @PostMapping(value = "")
    @ResponseBody
    public ResultMsg add(@RequestBody OauthUser body) {
        body.setPassword(passwordEncoder.encode(body.getPassword()));
        oauthUserService.save(body);
        return ResultMsg.success();
    }

    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids) {
        oauthUserService.deleteByIds(ids);
        return ResultMsg.success();
    }

    @ApiOperation(value = "更新个人信息")
    @PatchMapping
    @ResponseBody
    public ResultMsg update(@RequestBody OauthUser oauthUser, Authentication authentication) {
        oauthUserService.updateByPrimaryKeySelective(oauthUser);
        UserEntity userEntity = getCurrentUser(authentication);
        if (null != userEntity) {
            //更新session中的principal
            BeanUtils.copyPropertiesIgnoreNull(oauthUser, userEntity);
        }

        return ResultMsg.success();
    }

}
