package com.simon.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信工具类
 *
 * @author simon
 * @date 2018-12-03
 **/

@Slf4j
public class SmsUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static SmsUtil instance = new SmsUtil();

    /**
     * @see org.apache.http.client.HttpClient
     */
    private HttpClient httpclient = HttpClientBuilder.create().build();

    private SmsUtil() {
    }

    public static SmsUtil getInstance(){
        return instance;
    }

    /**
     * 发送短信
     *
     * @param clientid 帐号
     * @param password 密码
     * @param mobile 发送手机号码
     * @param content 短信内容
     * @param smsType 0：通知短信，4：验证码短信，5：营销短信
     */
    @SuppressWarnings("unchecked")
    public String sendSMS(String clientid, String password, String mobile, String content, String smsType) {
        Map<String, Object> param = getRequestParam(clientid, password, mobile, content, smsType);
        try {
            String uri = String.valueOf(param.get("uri"));
            HttpPost httppost = new HttpPost(uri);
            password = DigestUtils.md5Hex(String.valueOf(param.get("password")).getBytes(DEFAULT_CHARSET))
                    .toLowerCase();
            param.remove("uri");
            param.put("password", password);

            String params = JSON.toJSONString(param);
            HttpEntity requestEntity = new StringEntity(params, DEFAULT_CHARSET);
            httppost.setEntity(requestEntity);
            httppost.setHeader(HTTP.CONTENT_ENCODING, DEFAULT_CHARSET);
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8;");
            httppost.setHeader("Accept", "application/json");
            HttpResponse httpresponse = httpclient.execute(httppost);
            HttpEntity responseEntity = httpresponse.getEntity();
            InputStream in = responseEntity.getContent();
            if (in != null) {
                return IOUtils.toString(in, DEFAULT_CHARSET);
            }
        } catch (Exception e) {
            log.debug("发送短信验证码出错", e);
        }
        return null;
    }

    /**
     * 获取请求参数
     *
     * @param clientid clientid
     * @param password password
     * @param mobile mobile
     * @param content content
     * @param smsType smsType
     * @return map
     */
    private Map<String, Object> getRequestParam(String clientid, String password, String mobile, String content,
                                                String smsType) {

        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("uid", "00");
        requestParam.put("extend", "00");
        requestParam.put("smstype", smsType);
        requestParam.put("clientid", clientid);
        requestParam.put("password", password);
        requestParam.put("content", content);
        requestParam.put("mobile", mobile);
        requestParam.put("uri", "https://api.ucpaas.com/sms-partner/access/" + clientid + "/sendsms");
        return requestParam;
    }
}
