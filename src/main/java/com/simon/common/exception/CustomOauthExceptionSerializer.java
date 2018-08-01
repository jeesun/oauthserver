package com.simon.common.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.simon.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 序列化
 *
 * @author simon
 * @create 2018-05-31 18:16
 **/
@Slf4j
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        log.warn("CustomOauthException = " + value);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        gen.writeStartObject();
        gen.writeNumberField(AppConfig.CODE, value.getHttpErrorCode());
        gen.writeStringField(AppConfig.MESSAGE, value.getMessage());
        gen.writeNullField(AppConfig.DATA);
//        gen.writeStringField("message", "用户名或密码错误");
        //gen.writeStringField("path", request.getServletPath());
        //gen.writeStringField("timestamp", String.valueOf(new Date().getTime()));
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                log.warn("key = " + key);
                log.warn("add = " + add);
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}
