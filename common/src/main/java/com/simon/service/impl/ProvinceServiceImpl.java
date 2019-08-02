
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.mapper.ProvinceMapper;
import com.simon.model.Province;
import com.simon.repository.ProvinceRepository;
import com.simon.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-24
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class ProvinceServiceImpl extends CrudServiceImpl<Province, Integer> implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Cacheable(value = "chinaRegionCache", key = "'provinces'")
    @Override
    public List<CascaderOptionDto> getCascaderOptionDtos() {
        return provinceMapper.getCascaderOptionDtos();
    }
}