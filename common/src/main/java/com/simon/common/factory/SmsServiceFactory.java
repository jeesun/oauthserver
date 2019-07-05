package com.simon.common.factory;

import com.simon.common.utils.SpringBeanLoader;
import com.simon.service.BaseSmsService;
import com.simon.service.impl.AliSmsServiceImpl;
import com.simon.service.impl.TxSmsServiceImpl;
import com.simon.service.impl.YzxSmsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 简单工厂模式 + 饿汉式单例模式
 *
 * @author simon
 * @version 1.0
 * @date 2019-07-05 13:21
 */
@Slf4j
public class SmsServiceFactory {
    private BaseSmsService smsService;

    private static SmsServiceFactory instance = new SmsServiceFactory();

    private SmsServiceFactory() {
    }

    public static SmsServiceFactory getInstance() {
        return instance;
    }

    /**
     * @param type 可选值{"云之讯": "yzxSmsServiceImpl"，"阿里大鱼": "aliSmsServiceImpl", "腾讯云": "txSmsServiceImpl"}
     * @return
     */
    public BaseSmsService getSmsService(String type) {
        if (null != smsService) {
            log.info("返回缓存的smsService");
            return smsService;
        }
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        if ("yzxSmsServiceImpl".equals(type)) {
            smsService = SpringBeanLoader.getSpringBean(YzxSmsServiceImpl.class);
        }
        if ("aliSmsServiceImpl".equals(type)) {
            smsService = SpringBeanLoader.getSpringBean(AliSmsServiceImpl.class);
        }
        if ("txSmsServiceImpl".equals(type)) {
            smsService = SpringBeanLoader.getSpringBean(TxSmsServiceImpl.class);
        }
        return smsService;
    }
}
