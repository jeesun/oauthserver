
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.mapper.CityMapper;
import com.simon.model.City;
import com.simon.repository.CityRepository;
import com.simon.service.CityService;
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
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public long count() {
        return cityRepository.count();
    }

    @Override
    public City save(City city){
        return cityRepository.save(city);
    }

    @Override
    public List<City> save(List<City> cityList) {
        return cityRepository.save(cityList);
    }

    @Override
    public PageInfo<City> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<City> list = cityMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<City> findAll(Pageable pageable){
        return cityRepository.findAll(pageable);
    }

    @Override
    public List<City> findAll(){
        return cityRepository.findAll();
    }

    @Override
    public void delete(Integer id){
        cityRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return cityMapper.deleteByIds(ids);
    }

    @Override
    public City findById(Integer id){
        return cityRepository.findOne(id);
    }

    @Override
    public int insertList(List<City> list){
        return cityMapper.insertList(list);
    }

    @Override
    public int insert(City city){
        return cityMapper.insert(city);
    }

    @Override
    public int insertSelective(City city){
        return cityMapper.insertSelective(city);
    }

    @Override
    public int updateByPrimaryKey(City city){
        return cityMapper.updateByPrimaryKey(city);
    }

    @Override
    public int updateByPrimaryKeySelective(City city){
        return cityMapper.updateByPrimaryKeySelective(city);
    }

    @Override
    public PageInfo<City> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<City> list = cityMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<City> list) {

    }

    @Override
    public void batchUpdate(List<City> list) {

    }

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