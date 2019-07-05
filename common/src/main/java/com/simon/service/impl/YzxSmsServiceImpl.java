package com.simon.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simon.common.utils.SmsUtil;
import com.simon.dto.SmsResultDto;
import com.simon.service.BaseSmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 云之讯短信服务
 *
 * @author simon
 * @date 2018-12-03
 **/

@Slf4j
@Component
public class YzxSmsServiceImpl extends BaseSmsService {
    @Autowired
    protected org.springframework.cache.CacheManager cacheManager;

    private static final long EXPIRE_SECONDS = 300 * 1000;

    @Value("${sms.clientid}")
    private String clientid;

    @Value("${sms.password}")
    private String password;

    @Value("${sms.identity-code-msg-template}")
    private String identityCodeMsgTemplate;

    @Override
    public SmsResultDto sendSms(String nationCode, String mobile) {
        SmsResultDto smsResultDto = new SmsResultDto();
        int code = RandomUtils.nextInt(100000, 999999);
        smsResultDto.setCode(String.valueOf(code));
        String content = String.format(identityCodeMsgTemplate, code);
        String result = SmsUtil.getInstance().sendSMS(clientid, password, mobile, content, "4");
        log.error(result);
        String responseCode = null;
        if (StringUtils.isNotEmpty(result)) {
            JSONObject obj = JSONObject.parseObject(result);
            if (null != obj) {
                JSONArray jsonArray = obj.getJSONArray("data");
                if (jsonArray != null && !jsonArray.isEmpty()) {
                    JSONObject index = jsonArray.getJSONObject(0);
                    if (index != null) {
                        responseCode = index.getString("code");
                        if ("0".equals(responseCode)) {
                            smsResultDto.setResult(true);
                        } else {
                            smsResultDto.setResult(false);
                        }
                    }
                }
            }
        }
        return smsResultDto;
    }
}
