package com.simon.controller;

import com.simon.common.config.AppConfig;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.common.utils.ValidUtil;
import com.simon.service.SmsService;
import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author simon
 * @date 2019-01-02
 */
@Slf4j
@Api(description = "验证码")
@RestController
@RequestMapping("/api/veriCodes")
public class VeriCodeController extends BaseController {
    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    @Autowired
    @Qualifier(value = AppConfig.SMS_SERVICE_IMPL)
    private SmsService smsService;

    @ApiOperation(value = "获取验证码")
    @GetMapping("/sms/{phone}")
    public ResultMsg getVeriCodeByPhone(@PathVariable String phone) throws ApiException {
        if (!ValidUtil.isMobile(phone)) {
            return ResultMsg.fail(ResultCode.ERROR_INVALID_PHONE);
        }

        //测试手机号
        List<String> whiteList = new ArrayList<>();
        whiteList.add("18800000000");
        whiteList.add("18800000001");
        whiteList.add("18800000002");
        if (whiteList.contains(phone)){
            //写入缓存
            var cache = cacheManager.getCache("smsCache");
            cache.put(phone, "123456");
            return ResultMsg.success();
        }

        if (smsService.sendIdentifyCode(phone)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.fail(ResultCode.FAIL);
        }
    }
}
