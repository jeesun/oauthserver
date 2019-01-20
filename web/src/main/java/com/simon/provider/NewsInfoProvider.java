package com.simon.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
* 新闻
* @author SimonSun
* @date 2019-01-20
**/

public class NewsInfoProvider {
    /**
     * 构造SQL查询语句
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("tb1.*,tu1.username AS create_user,tu2.username AS update_user");
                FROM("t_news_info tb1");
                LEFT_OUTER_JOIN("t_users tu1 ON tb1.create_by = tu1.id");
                LEFT_OUTER_JOIN("t_users tu2 ON tb1.update_by = tu2.id");
                if (null != param.get("title") && StringUtils.isNotEmpty(String.valueOf(param.get("title")))) {
                    WHERE("tb1.title=#{title}");
                }
                if (null != param.get("status")) {
                    WHERE("tb1.status=#{status}");
                }
                if (null != param.get("newsType")) {
                    WHERE("tb1.news_type=#{newsType}");
                }
            }
        }.toString();
    }
}
