package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.Town;
import com.simon.provider.TownProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-25
**/
public interface TownMapper extends MyMapper<Town> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("BaseResultMap")
    @SelectProvider(type = TownProvider.class, method = "getList")
    List<Town> getList(Map<String, Object> map);
}