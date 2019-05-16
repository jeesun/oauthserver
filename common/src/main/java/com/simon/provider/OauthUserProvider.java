package com.simon.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
* 用户表
* @author SimonSun
* @date 2019-01-22
**/

public class OauthUserProvider {
    /**
     * 构造SQL查询语句
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("tb1.*,tu1.username AS create_user,tu2.username AS update_user");
                FROM("t_users tb1");
                LEFT_OUTER_JOIN("t_users tu1 ON tb1.create_by = tu1.id");
                LEFT_OUTER_JOIN("t_users tu2 ON tb1.update_by = tu2.id");
                if (null != param.get("username") && StringUtils.isNotEmpty(String.valueOf(param.get("username")))) {
                    WHERE("tb1.username=#{username}");
                }
                if (null != param.get("enabled")) {
                    WHERE("tb1.enabled=#{enabled}");
                }
                if (null != param.get("phone") && StringUtils.isNotEmpty(String.valueOf(param.get("phone")))) {
                    WHERE("tb1.phone=#{phone}");
                }
                if (null != param.get("email") && StringUtils.isNotEmpty(String.valueOf(param.get("email")))) {
                    WHERE("tb1.email=#{email}");
                }
                if (null != param.get("sex")) {
                    WHERE("tb1.sex=#{sex}");
                }
            }
        }.toString();
    }
}
