package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.service.SmsService;
import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author simon
 * @date 2019-01-02
 */
@Slf4j
@Api(value = "验证码", description = "验证码")
@RestController
@RequestMapping("/api/veriCodes")
public class VeriCodeController extends BaseController {

    @Autowired
    @Qualifier(value = "yzxSmsServiceImpl")
    private SmsService smsService;

    @ApiOperation(value = "获取验证码")
    @GetMapping("/sms/{phone}")
    public ResultMsg getVeriCodeByPhone(@PathVariable String phone) throws ApiException {
        if (smsService.sendIdentifyCode(phone)){
            return ResultMsg.success();
        }else{
            return ResultMsg.fail(ResultCode.FAIL);
        }
    }
}
