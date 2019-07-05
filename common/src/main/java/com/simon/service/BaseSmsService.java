package com.simon.service;

import com.simon.common.domain.ResultCode;
import com.simon.common.exception.BusinessException;
import com.simon.dto.SmsResultDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;

/**
 * 短信服务 模板模式
 *
 * @author simon
 * @date 2018-12-03
 **/
public abstract class BaseSmsService {
    @Autowired
    protected org.springframework.cache.CacheManager cacheManager;

    /**
     * 发送短信验证码，并把验证码写入缓存
     * @param nationCode 国家码 默认值：+86
     * @param mobile 手机号
     * @return 发送结果
     */
    public final boolean sendIdentifyCode(String nationCode, String mobile) {
        SmsResultDto smsResultDto = sendSms(nationCode, mobile);
        if (null != smsResultDto) {
            if (smsResultDto.getResult()) {
                //短信发送成功，写入缓存
                Cache cache = cacheManager.getCache("smsCache");
                cache.put(mobile, smsResultDto.getCode());
            }
            return true;
        }
        return false;
    }

    protected abstract SmsResultDto sendSms(String nationCode, String mobile);

    /**
     * 校验短信验证码
     * @param mobile 手机号
     * @param code 验证码
     * @return 验证结果
     */
    public final boolean checkCode(String mobile, String code) {
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
        return result;
    }
}
