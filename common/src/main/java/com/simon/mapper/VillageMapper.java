package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.model.Village;
import com.simon.provider.VillageProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-24
**/
public interface VillageMapper extends CrudMapper<Village> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("BaseResultMap")
    @SelectProvider(type = VillageProvider.class, method = "getList")
    List<Village> getList(Map<String, Object> map);
}