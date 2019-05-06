package com.simon.common.controller;

import com.simon.common.domain.UserEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
     * 字符串绑定Date类型
     * @param binder WebDataBinder对象
     */
    @InitBinder
    protected void initDateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //setLenient用于设置Calendar是否宽松解析字符串，如果为false，则严格解析；默认为true，宽松解析
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 字符串绑定Time类型
     * @param binder WebDataBinder对象
     */
    @InitBinder
    protected void initTimeBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Time.class, new CustomDateEditor(dateFormat, true));
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
