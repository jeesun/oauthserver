package com.simon.common.utils;

import com.google.common.base.Joiner;
import com.simon.model.DictType;
import com.simon.service.DictTypeService;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 字典工具类
 *
 * @author simon
 * @create 2018-09-06 11:25
 **/

public class DictUtil {
    private static ApplicationContext applicationContext = SpringUtils.getApplicationContext();
    private static DictTypeService dictTypeService = applicationContext.getBean(DictTypeService.class);

    /**
     * 字典查询
     * @param groupCode 字典组编码
     * @param typeCode 字典编码（多个逗号拼接）
     * @return 字典编码名称（多个逗号拼接）
     */
    public static String getTypeName(String groupCode, String typeCode){
        var types = dictTypeService.getTypeByGroupCode(groupCode);
        if(null == types || types.size() <= 0){
            return null;
        }
        if(StringUtils.isEmpty(typeCode)){
            return null;
        }

        String[] typeCodes = typeCode.split(",");
        String[] typeCodeNames = new String[typeCodes.length];

        for(var i = 0; i < typeCodes.length; i++){
            var code = typeCodes[i];
            for(var j = 0; j < types.size(); j++){
                var type = types.get(j);
                if(code.equals(type.getTypeCode())){
                    typeCodeNames[i] = type.getTypeName();
                    break;
                }
            }
            if(StringUtils.isEmpty(typeCodeNames[i])){
                typeCodeNames[i] = "";
            }
        }

        //数组合并成字符串，用逗号隔开
        return Joiner.on(",").join(typeCodeNames);
    }

    /**
     * 获取字典组编码对应的字典列表
     * @param groupCode 字典组编码
     * @return 字典编码名称（多个逗号拼接）
     */
    public static List<DictType> getTypeNames(String groupCode){
        return dictTypeService.getTypeByGroupCode(groupCode);
    }
}
