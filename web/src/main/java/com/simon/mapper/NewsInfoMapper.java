package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.NewsInfo;
import com.simon.provider.NewsInfoProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-01-20
**/
public interface NewsInfoMapper extends MyMapper<NewsInfo> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @SelectProvider(type = NewsInfoProvider.class, method = "getList")
    List<NewsInfo> getList(Map<String, Object> map);
}