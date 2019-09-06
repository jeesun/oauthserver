package com.simon.common.plugins.wxpay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信支付参数
 *
 * @author simon
 * @date 2018-11-20
 **/
@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class WxPayProperties {
    /**
     * 设置微信公众号或者小程序等的appid
     */
    @Value("${wx.pay.appId}")
    private String appId;

    /**
     * 微信支付商户号
     */
    @Value("${wx.pay.mchId}")
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    @Value("${wx.pay.mchKey}")
    private String mchKey;

    /**
     * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
     */
    @Value("${wx.pay.subAppId}")
    private String subAppId;

    /**
     * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
     */
    @Value("${wx.pay.subMchId}")
    private String subMchId;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    @Value("${wx.pay.keyPath}")
    private String keyPath;

    @Value("${wx.pay.notifyUrl}")
    private String notifyUrl;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
