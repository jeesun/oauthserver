package com.simon.common.plugins.alipay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付宝配置
 *
 * @author simon
 * @date 2018-11-20
 **/
@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class AliPayConfig {
    // 商户appid
    @Value("${alipay.appid}")
    public String APPID;

    // 私钥 pkcs8格式的
    @Value("${alipay.rsa-private-key}")
    public String RSA_PRIVATE_KEY;

    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // notify_url为服务器通知，支付宝可以保证99.9999%的通知到达率，前提是您的网络通畅。
    @Value("${alipay.notify-url}")
    public String notify_url;

    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    // return_url为网页重定向通知，是由客户的浏览器触发的一个通知，若客户去网银支付，也会受银行接口影响，由于各种影响因素特别多，所以该种类型的通知支付宝不保证其到达率。
    @Value("${alipay.return-url}")
    public String return_url;

    // 请求网关地址
    @Value("${alipay.url}")
    public String URL;

    // 编码
    @Value("${alipay.charset}")
    public String CHARSET;

    // 返回格式
    @Value("${alipay.format}")
    public String FORMAT;

    // 支付宝公钥
    @Value("${alipay.alipay-public-key}")
    public String ALIPAY_PUBLIC_KEY;

    // RSA2
    @Value("${alipay.sign-type}")
    public String SIGNTYPE;
}