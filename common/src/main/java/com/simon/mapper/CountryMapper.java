package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.model.Country;
import com.simon.provider.CountryProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-24
**/
public interface CountryMapper extends CrudMapper<Country> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @Override
    @ResultMap("BaseResultMap")
    @SelectProvider(type = CountryProvider.class, method = "getList")
    List<Country> getList(Map<String, Object> map);

    /**
     * 根据城市id获取区列表
     * @param cityId 城市id
     * @return 区列表
     */
    List<Country> findByCityId(@Param("cityId") String cityId);

    /**
     * 根据城市id获取区列表
     * @param cityId 城市id
     * @return 区列表
     */
    List<CascaderOptionDto> getCascaderOptionDtos(@Param("cityId") String cityId);
}