package com.simon.service.impl;

import com.simon.service.SmsService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 阿里大于短信验证码服务
 *
 * @author simon
 * @date 2019-01-02
 **/
@Slf4j
@Service(value = "aliSmsServiceImpl")
@Transactional(rollbackFor = {Exception.class})
public class AliSmsServiceImpl implements SmsService {
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

    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    @Override
    public boolean sendIdentifyCode(String mobile) throws ApiException {
        var code = RandomUtils.nextInt(100000, 999999);
        var client = new DefaultTaobaoClient(
                DAYU_URL_REAL, DAYU_APP_KEY, DAYU_APP_SECRET);
        var req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType(DAYU_SMS_TYPE);
        req.setSmsFreeSignName(DAYU_SMS_FREE_SIGN_NAME);
        req.setSmsParamString("{veriCode:'"+code+"'}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode(DAYU_SMS_TEMPLATE_CODE);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        rsp = client.execute(req);
        BizResult bizResult = rsp.getResult();
        if (null != bizResult && bizResult.getSuccess()){
            //写入缓存
            var cache = cacheManager.getCache("smsCache");
            cache.put(mobile, code);

            return true;
        }else{
            log.error("请确认阿里大于账号还有余额");
            return false;
        }
    }

    @Override
    public boolean checkCode(String mobile, String code) {
        log.info("checkCode");
        var cache = cacheManager.getCache("smsCache");
        var ele = cache.get(mobile);
        String output = (ele == null ? null : ele.get().toString());
        log.info("从缓存中读到" + mobile + "," + output);

        var result = false;

        if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(output)) {
            if (code.equals(output)) {
                result = true;
                //cache.evict(mobile);//删除
            }
        }

        //删除缓存
        cache.evict(mobile);

        return result;
    }
}
