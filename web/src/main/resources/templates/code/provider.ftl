package com.simon.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
* ${tableComment}
* @author ${AUTHOR}
* @date ${CREATE}
**/

public class ${entityName}Provider {
    /**
     * 构造SQL查询语句
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("tb1.*,tu1.username AS create_user,tu2.username AS update_user");
                FROM("${tableName} tb1");
                LEFT_OUTER_JOIN("t_users tu1 ON tb1.create_by = tu1.id");
                LEFT_OUTER_JOIN("t_users tu2 ON tb1.update_by = tu2.id");
    <#list columns as column>
        <#if column.allowSearch>
            <#switch column.type>
                <#case "String">
                if (null != param.get("${column.name}") && StringUtils.isNotEmpty(String.valueOf(param.get("${column.name}")))) {
                    WHERE("tb1.${column.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}=${r"#{" + column.name + r"}"}");
                }
                <#break>
                <#default>
                if (null != param.get("${column.name}")) {
                    WHERE("tb1.${column.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}=${r"#{" + column.name + r"}"}");
                }
            </#switch>
        </#if>
    </#list>
            }
        }.toString();
    }
}
