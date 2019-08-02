
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.mapper.CountryMapper;
import com.simon.model.Country;
import com.simon.repository.CountryRepository;
import com.simon.service.CountryService;
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
public class CountryServiceImpl extends CrudServiceImpl<Country, Integer> implements CountryService {
    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> findByCityId(String cityId) {
        return countryMapper.findByCityId(cityId);
    }

    @Cacheable(value = "chinaRegionCache", key = "'cityId' + #cityId")
    @Override
    public List<CascaderOptionDto> getCascaderOptionDtos(String cityId) {
        return countryMapper.getCascaderOptionDtos(cityId);
    }
}