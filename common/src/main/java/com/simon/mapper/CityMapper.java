package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.model.City;
import com.simon.provider.CityProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-24
**/
public interface CityMapper extends CrudMapper<City> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("BaseResultMap")
    @SelectProvider(type = CityProvider.class, method = "getList")
    List<City> getList(Map<String, Object> map);

    /**
     * 根据省id查询省下面的城市
     * @param provinceId 省id
     * @return 城市列表
     */
    List<City> findByProvinceId(@Param("provinceId") String provinceId);

    /**
     * 根据省id查询省下面的城市
     * @param provinceId 省id
     * @return 城市列表
     */
    List<CascaderOptionDto> getCascaderOptionDtos(@Param("provinceId") String provinceId);
}