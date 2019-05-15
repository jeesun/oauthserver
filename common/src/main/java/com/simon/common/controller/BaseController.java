package com.simon.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.simon.common.domain.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Base Controller
 *
 * @author simon
 * @create 2018-09-06 17:49
 **/

public class BaseController {
    /**
     * 字符串绑定java8时间类型
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        });
        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        });
    }

    /**
     * 解决Long类型数据返回前端丢失精度问题。
     * 注意，Long类型属性必须添加注解@JSONField(serializeUsing = ToStringSerializer.class)，因为用的fastjson
     * @param entity 要转换的实体对象
     * @return map
     */
    @SuppressWarnings("unchecked")
    protected static Map<String, String> entityToMap(Object entity) {
        if (null == entity) {
            return null;
        }
        Map<String, String> map = (Map<String, String>) JSONObject.parse(JSON.toJSONString(entity, SerializerFeature.WriteMapNullValue));
        return map;
    }

    /**
     * 解决Long类型数据返回前端丢失精度问题。
     * 注意，Long类型属性必须添加注解@JSONField(serializeUsing = ToStringSerializer.class)，因为用的fastjson
     * @param list 要转换的list
     * @return listMap
     */
    @SuppressWarnings("unchecked")
    protected static List<Map<String, String>> listToMap(List<?> list){
        List<Map<String, String>> mapList = new ArrayList<>();
        if (null == list || list.size() <= 0) {
            return mapList;
        }
        for(Object item : list){
            Map<String, String> map = (Map<String, String>) JSONObject.parse(JSON.toJSONString(item, SerializerFeature.WriteMapNullValue));
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 获取当前登录用户
     * @param authentication Spring Security框架自动注入
     * @return 当前登录用户
     */
    protected static UserEntity getCurrentUser(Authentication authentication){
        Object principal = authentication.getPrincipal();
        UserEntity userEntity = null;
        if (principal instanceof UserEntity) {
            userEntity = (UserEntity) principal;
        }
        return userEntity;
    }
}
