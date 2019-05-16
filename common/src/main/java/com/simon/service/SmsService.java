package com.simon.service;

/**
 * 短信服务
 *
 * @author simon
 * @date 2018-12-03
 **/

public interface SmsService {
    /**
     * 发送短信验证码，并把验证码写入缓存
     * @param mobile 手机号
     * @return 发送结果
     */
    boolean sendIdentifyCode(String mobile);

    /**
     * 校验短信验证码
     * @param mobile 手机号
     * @param code 验证码
     * @return 验证结果
     */
    boolean checkCode(String mobile, String code);
}
