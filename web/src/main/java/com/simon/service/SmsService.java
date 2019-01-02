package com.simon.service;

import com.taobao.api.ApiException;

/**
 * 短信服务
 *
 * @author simon
 * @date 2018-12-03
 **/

public interface SmsService {
    boolean sendIdentifyCode(String mobile) throws ApiException;

    boolean checkCode(String mobile,String code);
}
