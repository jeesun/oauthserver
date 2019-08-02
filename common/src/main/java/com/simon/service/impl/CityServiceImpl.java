
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.mapper.CityMapper;
import com.simon.model.City;
import com.simon.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author SimonSun
* @date 2019-04-24
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class CityServiceImpl extends CrudServiceImpl<City, Integer> implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> findByProvinceId(String provinceId) {
        return cityMapper.findByProvinceId(provinceId);
    }

    @Cacheable(value = "chinaRegionCache", key = "'provinceId' + #provinceId")
    @Override
    public List<CascaderOptionDto> getCascaderOptionDtos(String provinceId) {
        return cityMapper.getCascaderOptionDtos(provinceId);
    }
}