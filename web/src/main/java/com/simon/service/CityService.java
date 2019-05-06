package com.simon.service;

import com.simon.common.service.BasicService;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.model.City;

import java.util.List;

/**
* @author SimonSun
* @date 2019-04-24
**/
public interface CityService extends BasicService<City, Integer> {
    /**
     * 根据省id查询省下面的城市
     * @param provinceId 省id
     * @return 城市列表
     */
    List<City> findByProvinceId(String provinceId);

    /**
     * 根据省id查询省下面的城市
     * @param provinceId 省id
     * @return 城市列表
     */
    List<CascaderOptionDto> getCascaderOptionDtos(String provinceId);
}