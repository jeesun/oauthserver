
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.mapper.CountryMapper;
import com.simon.model.Country;
import com.simon.repository.CountryRepository;
import com.simon.service.CountryService;
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
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public long count() {
        return countryRepository.count();
    }

    @Override
    public Country save(Country country){
        return countryRepository.save(country);
    }

    @Override
    public List<Country> save(List<Country> countryList) {
        return countryRepository.save(countryList);
    }

    @Override
    public PageInfo<Country> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Country> list = countryMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<Country> findAll(Pageable pageable){
        return countryRepository.findAll(pageable);
    }

    @Override
    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    @Override
    public void delete(Integer id){
        countryRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return countryMapper.deleteByIds(ids);
    }

    @Override
    public Country findById(Integer id){
        return countryRepository.findOne(id);
    }

    @Override
    public int insertList(List<Country> list){
        return countryMapper.insertList(list);
    }

    @Override
    public int insert(Country country){
        return countryMapper.insert(country);
    }

    @Override
    public int insertSelective(Country country){
        return countryMapper.insertSelective(country);
    }

    @Override
    public int updateByPrimaryKey(Country country){
        return countryMapper.updateByPrimaryKey(country);
    }

    @Override
    public int updateByPrimaryKeySelective(Country country){
        return countryMapper.updateByPrimaryKeySelective(country);
    }

    @Override
    public PageInfo<Country> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Country> list = countryMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<Country> list) {

    }

    @Override
    public void batchUpdate(List<Country> list) {

    }

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