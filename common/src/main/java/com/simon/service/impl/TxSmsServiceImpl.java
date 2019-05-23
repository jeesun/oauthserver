package com.simon.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.simon.common.domain.ResultCode;
import com.simon.common.exception.BusinessException;
import com.simon.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * 腾讯云短信服务
 *
 * @author simon
 * @date 2019-05-13
 **/
@Slf4j
@Service(value = "txSmsServiceImpl")
@Transactional(rollbackFor = {Exception.class})
public class TxSmsServiceImpl implements SmsService {
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

    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    @Override
    public boolean sendIdentifyCode(String nationCode, String mobile) {
        if (nationCode.startsWith("+")) {
            nationCode = nationCode.substring(1);
        }
        SmsSingleSender sender = new SmsSingleSender(appId, appKey);
        int code = RandomUtils.nextInt(100000, 999999);
        try {
            SmsSingleSenderResult result = sender.sendWithParam(nationCode, mobile, templateId, new String[]{String.valueOf(code)}, sign, "", "");
            if (0 == result.result) {
                //短信发送成功，写入缓存
                //写入缓存
                Cache cache = cacheManager.getCache("smsCache");
                cache.put(mobile, code);
            }
        } catch (HTTPException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkCode(String mobile, String code) {
        Cache cache = cacheManager.getCache("smsCache");
        Cache.ValueWrapper ele = cache.get(mobile);

        if (null == ele) {
            throw new BusinessException(ResultCode.ERROR_VERI_CODE);
        }

        String output = ele.get().toString();

        boolean result = false;

        if (StringUtils.isEmpty(output)) {
            throw new BusinessException(ResultCode.ERROR_VERI_CODE);
        }

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
