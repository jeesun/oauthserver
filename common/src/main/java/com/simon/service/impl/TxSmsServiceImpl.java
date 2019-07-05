package com.simon.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.simon.dto.SmsResultDto;
import com.simon.service.BaseSmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 腾讯云短信服务
 *
 * @author simon
 * @date 2019-05-13
 **/
@Slf4j
@Component
public class TxSmsServiceImpl extends BaseSmsService {
    @Autowired
    protected org.springframework.cache.CacheManager cacheManager;

    /**
     * 短信应用 SDK AppID
     */
    @Value("${com.github.qcloudsms.appid}")
    private Integer appId;

    /**
     * 短信应用 SDK AppKey
     */
    @Value("${com.github.qcloudsms.app-key}")
    private String appKey;

    /**
     * 短信模板 ID，需要在短信应用中申请
     */
    @Value("${com.github.qcloudsms.template-id}")
    private Integer templateId;

    /**
     * 签名。签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
     */
    @Value("${com.github.qcloudsms.sign}")
    private String sign;


    @Override
    public SmsResultDto sendSms(String nationCode, String mobile) {
        SmsResultDto smsResultDto = new SmsResultDto();
        if (nationCode.startsWith("+")) {
            nationCode = nationCode.substring(1);
        }
        SmsSingleSender sender = new SmsSingleSender(appId, appKey);
        int code = RandomUtils.nextInt(100000, 999999);
        smsResultDto.setCode(String.valueOf(code));
        try {
            SmsSingleSenderResult result = sender.sendWithParam(nationCode, mobile, templateId, new String[]{String.valueOf(code)}, sign, "", "");
            if (0 == result.result) {
                smsResultDto.setResult(true);
            } else {
                smsResultDto.setResult(false);
                smsResultDto.setErrMsg(result.errMsg);
            }
        } catch (HTTPException | IOException e) {
            e.printStackTrace();
            smsResultDto.setResult(false);
            smsResultDto.setErrMsg(e.getMessage());
        }
        return smsResultDto;
    }
}
