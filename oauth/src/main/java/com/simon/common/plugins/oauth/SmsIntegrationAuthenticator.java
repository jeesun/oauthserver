package com.simon.common.plugins.oauth;

import com.simon.common.config.AppConfig;
import com.simon.common.domain.UserEntity;
import com.simon.common.factory.SmsServiceFactory;
import com.simon.service.BaseSmsService;
import com.simon.service.OauthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author simon
 * @date 2018-11-30
 **/

@Slf4j
@Component
public class SmsIntegrationAuthenticator extends AbstractPreparableIntegrationAuthenticator implements ApplicationEventPublisherAware {

    @Autowired
    private OauthUserService oauthUserService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    private ApplicationEventPublisher applicationEventPublisher;

    private final static String SMS_AUTH_TYPE = "sms";

    @Override
    public UserEntity authenticate(IntegrationAuthentication integrationAuthentication) {
        log.info("sms authenticate");
        //获取密码，实际值是验证码
        String password = integrationAuthentication.getAuthParameter("password");
        //获取用户名，实际值是手机号
        String username = integrationAuthentication.getUsername();
        //发布事件，可以监听事件进行自动注册用户
        //this.applicationEventPublisher.publishEvent(new SmsAuthenticateBeforeEvent(integrationAuthentication));

        UserEntity userVo = null;
        BaseSmsService smsService = SmsServiceFactory.getInstance().getSmsService(AppConfig.SMS_SERVICE_IMPL);
        //校验验证码
        if(smsService.checkCode(username, password)){
            log.info("验证码正确");
            //通过手机号码查询用户
            userVo = this.oauthUserService.findEntityByPhone(username);

            if (userVo != null) {
                //将密码设置为验证码
                userVo.setPassword(passwordEncoder.encode(password));
                //发布事件，可以监听事件进行消息通知
                //this.applicationEventPublisher.publishEvent(new SmsAuthenticateSuccessEvent(integrationAuthentication));
            }
        }

        return userVo;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        log.info("sms prepare");
        String smsToken = integrationAuthentication.getAuthParameter("sms_token");
        String smsCode = integrationAuthentication.getAuthParameter("password");
        String username = integrationAuthentication.getAuthParameter("username");
        /*Result<Boolean> result = vccClient.validate(smsToken, smsCode, username);
        if (!result.getData()) {
            throw new OAuth2Exception("验证码错误或已过期");
        }*/
        log.info("smsToken=" + smsToken + ",smsCode=" + smsCode + ",username=" + username);
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return SMS_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}