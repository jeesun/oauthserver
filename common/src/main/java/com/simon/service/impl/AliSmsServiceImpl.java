package com.simon.service.impl;

import com.simon.dto.SmsResultDto;
import com.simon.service.BaseSmsService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里大于短信验证码服务
 *
 * @author simon
 * @date 2019-01-02
 **/
@Slf4j
@Component
public class AliSmsServiceImpl extends BaseSmsService {
    @Autowired
    protected org.springframework.cache.CacheManager cacheManager;

    @Value("${com.alibaba.dayu.url.sandbox}")
    private String DAYU_URL_SANDBOX;

    @Value("${com.alibaba.dayu.url.real}")
    private String DAYU_URL_REAL;

    @Value("${com.alibaba.dayu.app-key}")
    private String DAYU_APP_KEY;

    @Value("${com.alibaba.dayu.app-secret}")
    private String DAYU_APP_SECRET;

    @Value("${com.alibaba.dayu.sms-type}")
    private String DAYU_SMS_TYPE;

    @Value("${com.alibaba.dayu.sms-free-sign-name}")
    private String DAYU_SMS_FREE_SIGN_NAME;

    @Value("${com.alibaba.dayu.sms-template-code}")
    private String DAYU_SMS_TEMPLATE_CODE;

    @Override
    public SmsResultDto sendSms(String nationCode, String mobile) {
        SmsResultDto smsResultDto = new SmsResultDto();
        int code = RandomUtils.nextInt(100000, 999999);
        smsResultDto.setCode(String.valueOf(code));
        DefaultTaobaoClient client = new DefaultTaobaoClient(
                DAYU_URL_REAL, DAYU_APP_KEY, DAYU_APP_SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType(DAYU_SMS_TYPE);
        req.setSmsFreeSignName(DAYU_SMS_FREE_SIGN_NAME);
        req.setSmsParamString("{veriCode:'" + code + "'}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode(DAYU_SMS_TEMPLATE_CODE);
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            BizResult bizResult = rsp.getResult();
            if (null != bizResult && bizResult.getSuccess()) {
                smsResultDto.setResult(true);
            } else {
                smsResultDto.setResult(false);
                smsResultDto.setErrMsg("请确认大鱼还有余额");
            }
        } catch (ApiException e) {
            e.printStackTrace();
            log.error(e.getErrMsg());
            smsResultDto.setResult(false);
            smsResultDto.setErrMsg(e.getErrMsg());
        }
        return smsResultDto;
    }
}
