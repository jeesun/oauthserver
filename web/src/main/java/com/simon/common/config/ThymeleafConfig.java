package com.simon.common.config;

import com.simon.common.plugins.qiniu.QiNiuConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * thymeleaf配置
 *
 * @author simon
 * @date 2018-11-02
 **/
@Component
@Configuration
public class ThymeleafConfig {
    //@Value("${server.port}")
    private String serverPort = AppConfig.SERVER_PORT;

    //@Value("${file.upload.type}")
    private String fileUploadType = AppConfig.FILE_UPLOAD_TYPE;

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars = new LinkedHashMap<>();
            //thymeleaf添加全局静态变量
            vars.put("helloWorld", "Hello World");
            if (AppConfig.FILE_UPLOAD_TYPE_QINIU.equals(fileUploadType)){
                vars.put("filePathPrefix", QiNiuConfig.getInstance().getPublicDomainOfBucket());
            }else{
                vars.put("filePathPrefix", "http://localhost:" + serverPort);
            }
            viewResolver.setStaticVariables(vars);
        }
    }
}
