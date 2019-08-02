package com.simon.service;

import com.simon.common.service.CrudService;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.model.Country;

import java.util.List;

/**
* @author SimonSun
* @date 2019-04-24
**/
public interface CountryService extends CrudService<Country, Integer> {
    /**
     * 根据城市id获取区列表
     * @param cityId 城市id
     * @return 区列表
     */
    List<Country> findByCityId(String cityId);

    /**
     * 根据城市id获取区列表
     * @param cityId 城市id
     * @return 区列表
     */
    List<CascaderOptionDto> getCascaderOptionDtos(String cityId);
}