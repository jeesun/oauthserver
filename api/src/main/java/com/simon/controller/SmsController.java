package com.simon.controller;

import com.simon.common.config.AppConfig;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.common.factory.SmsServiceFactory;
import com.simon.common.utils.ValidUtil;
import com.simon.service.BaseSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * @author simon
 * @date 2019-01-02
 */
@Slf4j
@Api(description = "验证码")
@RestController
@RequestMapping("/api/sms")
public class SmsController extends BaseController {
    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    @PermitAll
    @ApiOperation(value = "获取验证码")
    @GetMapping("/verifyCode")
    public ResponseEntity<ResultMsg> getVeriCodeByPhone(
            @ApiParam(value = "国家码", required = true, defaultValue = "+86", example = "+86") @RequestParam String nationCode,
            @ApiParam(value = "手机号", required = true) @RequestParam String mobile) {
        if (!ValidUtil.isMobile(mobile)) {
            //400 Bad Request客户端请求的语法错误，服务器无法理解
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultMsg.fail(ResultCode.ERROR_INVALID_PHONE));
        }

        //测试手机号
        /*List<String> whiteList = new ArrayList<>();
        whiteList.add("18800000000");
        whiteList.add("18800000001");
        whiteList.add("18800000002");
        if (whiteList.contains(mobile)) {
            //写入缓存
            var cache = cacheManager.getCache("smsCache");
            cache.put(mobile, "123456");
            return ResultMsg.success();
        }*/

        BaseSmsService smsService = SmsServiceFactory.getInstance().getSmsService(AppConfig.SMS_SERVICE_IMPL);
        if (smsService.sendIdentifyCode(nationCode, mobile)) {
            return ResponseEntity.ok(ResultMsg.success());
        } else {
            //500 Internal Server Error服务器内部错误，无法完成请求
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultMsg.fail(ResultCode.FAIL_SEND_SMS));
        }
    }

    @ApiOperation(value = "校验验证码，仅用于测试")
    @GetMapping("checkCode")
    public ResponseEntity<ResultMsg> checkCode(
            @ApiParam(value = "手机号", required = true) @RequestParam String mobile,
            @ApiParam(value = "验证码", required = true) @RequestParam String code) {
        BaseSmsService smsService = SmsServiceFactory.getInstance().getSmsService(AppConfig.SMS_SERVICE_IMPL);
        return ResponseEntity.ok(ResultMsg.success(smsService.checkCode(mobile, code)));
    }
}
