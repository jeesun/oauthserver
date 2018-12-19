package com.simon.service;

/**
 * 短信服务
 *
 * @author simon
 * @date 2018-12-03
 **/

public interface SmsService {
    boolean sendIdentifyCode(String mobile);

    boolean checkCode(String mobile,String code);
}
