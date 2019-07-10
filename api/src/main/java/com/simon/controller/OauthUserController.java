package com.simon.controller;

import com.simon.common.config.AppConfig;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.common.domain.UserEntity;
import com.simon.common.exception.RegisterException;
import com.simon.common.factory.SmsServiceFactory;
import com.simon.common.utils.BeanUtils;
import com.simon.dto.OauthUserDto;
import com.simon.model.OauthUser;
import com.simon.service.BaseSmsService;
import com.simon.service.OauthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation("获取用户信息")
    @GetMapping("/userInfo")
    @ResponseBody
    public ResponseEntity<ResultMsg> getUserInfo(@ApiIgnore @ApiParam(hidden = true) Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        if (null != userEntity) {
            return ResponseEntity.ok(ResultMsg.success(userEntity));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultMsg.fail(ResultCode.FAIL));
    }

    @ApiOperation(value = "注册：手机号+验证码")
    @PermitAll
    @PostMapping("/register/phoneAndCode")
    @ResponseBody
    public ResponseEntity<ResultMsg> register(
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
            return ResponseEntity.ok(ResultMsg.success());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultMsg.fail(ResultCode.ERROR_VERI_CODE));
        }
    }

    @ApiOperation(value = "注册: 手机号+密码")
    @PermitAll
    @PostMapping("/register/phoneAndPassword")
    @ResponseBody
    public ResponseEntity<ResultMsg> registerByPhoneAndPassword(
            @ApiParam(value = "区号", required = true, defaultValue = "+86") @RequestParam String areaCode,
            @ApiParam(value = "手机号", required = true) @RequestParam String phone,
            @ApiParam(value = "密码", required = true) @RequestParam String password) {
        oauthUserService.registerByPhoneAndPassword(areaCode, phone, password);
        return ResponseEntity.ok(ResultMsg.success());
    }

    @ApiOperation(value = "注册：邮箱+密码")
    @PermitAll
    @PostMapping("register/emailAndPassword")
    @ResponseBody
    public ResponseEntity<ResultMsg> registerByEmailAndPassword(
            @ApiParam(value = "邮箱", required = true) @RequestParam String email,
            @ApiParam(value = "密码", required = true) @RequestParam String password) {
        oauthUserService.registerByEmailAndPassword(email, password);
        return ResponseEntity.ok(ResultMsg.success());
    }

    @ApiOperation(value = "注册：用户名+密码")
    @PermitAll
    @PostMapping("register/usernameAndPassword")
    @ResponseBody
    public ResponseEntity<ResultMsg> registerByUsernameAndPassword(
            @ApiParam(value = "用户名", required = true) @RequestParam String username,
            @ApiParam(value = "密码", required = true) @RequestParam String password) {
        oauthUserService.registerByUsernameAndPassword(username, password);
        return ResponseEntity.ok(ResultMsg.success());
    }

    @ApiOperation(value = "更新个人信息")
    @PatchMapping
    @ResponseBody
    public ResultMsg update(@RequestBody OauthUserDto oauthUserDto, @ApiIgnore @ApiParam(hidden = true) Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        if (null != userEntity) {
            //更新session中的principal
            BeanUtils.copyPropertiesIgnoreNull(oauthUserDto, userEntity);
        }
        OauthUser oauthUser = new OauthUser();
        oauthUser.setId(userEntity.getId());
        BeanUtils.copyPropertiesIgnoreNull(oauthUserDto, oauthUser);
        oauthUserService.updateByPrimaryKeySelective(oauthUser);
        return ResultMsg.success();
    }

    @ApiOperation(value = "根据手机号和验证码重置密码")
    @PatchMapping("/updatePwd/phoneAndCode")
    @ResponseBody
    public ResponseEntity<ResultMsg> updatePwdByPhoneAndCode(
            @ApiParam(value = "手机号", required = true) @RequestParam String phone,
            @ApiParam(value = "验证码", required = true) @RequestParam String code,
            @ApiParam(value = "密码", required = true) @RequestParam String password) {
        oauthUserService.updatePwdByPhoneAndCode(phone, code, password);
        return ResponseEntity.ok(ResultMsg.success());
    }

    @ApiOperation(value = "根据旧密码更新密码")
    @PatchMapping("/updatePwd/oldPwd")
    @ResponseBody
    public ResponseEntity<ResultMsg> updatePwdByOldPwd(
            @ApiIgnore @ApiParam(hidden = true) Authentication authentication,
            @ApiParam(value = "旧密码", required = true) @RequestParam String oldPwd,
            @ApiParam(value = "新密码", required = true) @RequestParam String newPwd) {
        UserEntity userEntity = getCurrentUser(authentication);
        oauthUserService.updatePwdByOldPwd(userEntity.getUsername(), oldPwd, newPwd);
        return ResponseEntity.ok(ResultMsg.success());
    }
}
