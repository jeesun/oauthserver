package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.model.NewsInfo;
import com.simon.provider.NewsInfoProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-01-20
**/
public interface NewsInfoMapper extends CrudMapper<NewsInfo> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @Override
    @ResultMap("BaseResultMap")
    @SelectProvider(type = NewsInfoProvider.class, method = "getList")
    List<NewsInfo> getList(Map<String, Object> map);
}