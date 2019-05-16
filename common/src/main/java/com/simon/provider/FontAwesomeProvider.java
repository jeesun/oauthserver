package com.simon.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Font Awesome字体
 *
 * @author SimonSun
 * @date 2019-04-29
 **/

public class FontAwesomeProvider {
    /**
     * 构造SQL查询语句
     *
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("tb1.*,tu1.username AS create_user,tu2.username AS update_user");
                FROM("t_s_font_awesome tb1");
                LEFT_OUTER_JOIN("t_users tu1 ON tb1.create_by = tu1.id");
                LEFT_OUTER_JOIN("t_users tu2 ON tb1.update_by = tu2.id");
                if (null != param.get("label") && StringUtils.isNotEmpty(String.valueOf(param.get("label")))) {
                    WHERE("tb1.label LIKE CONCAT('%', #{label}, '%')");
                }
                if (null != param.get("tags") && StringUtils.isNotEmpty(String.valueOf(param.get("tags")))) {
                    WHERE("tb1.tags LIKE CONCAT('%', #{tags}, '%')");
                }
            }
        }.toString();
    }
}
