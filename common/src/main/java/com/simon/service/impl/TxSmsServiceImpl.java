package com.simon.service.impl;

import com.simon.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    @Override
    public boolean sendIdentifyCode(String mobile) {
        return false;
    }

    @Override
    public boolean checkCode(String mobile, String code) {
        return false;
    }
}
