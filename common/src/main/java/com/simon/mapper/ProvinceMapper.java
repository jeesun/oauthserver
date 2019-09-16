package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.model.Province;
import com.simon.provider.ProvinceProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-24
**/
public interface ProvinceMapper extends CrudMapper<Province> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @Override
    @ResultMap("BaseResultMap")
    @SelectProvider(type = ProvinceProvider.class, method = "getList")
    List<Province> getList(Map<String, Object> map);

    List<CascaderOptionDto> getCascaderOptionDtos();
}