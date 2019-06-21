
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
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
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public long count() {
        return provinceRepository.count();
    }

    @Override
    public Province save(Province province){
        return provinceRepository.save(province);
    }

    @Override
    public List<Province> save(List<Province> provinceList) {
        return provinceRepository.save(provinceList);
    }

    @Override
    public PageInfo<Province> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Province> list = provinceMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<Province> findAll(Pageable pageable){
        return provinceRepository.findAll(pageable);
    }

    @Override
    public List<Province> findAll(){
        return provinceRepository.findAll();
    }

    @Override
    public void delete(Integer id){
        provinceRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return provinceMapper.deleteByIds(ids);
    }

    @Override
    public Province findById(Integer id){
        return provinceRepository.findOne(id);
    }

    @Override
    public int insertList(List<Province> list){
        return provinceMapper.insertList(list);
    }

    @Override
    public int insert(Province province){
        return provinceMapper.insert(province);
    }

    @Override
    public int insertSelective(Province province){
        return provinceMapper.insertSelective(province);
    }

    @Override
    public int updateByPrimaryKey(Province province){
        return provinceMapper.updateByPrimaryKey(province);
    }

    @Override
    public int updateByPrimaryKeySelective(Province province){
        return provinceMapper.updateByPrimaryKeySelective(province);
    }

    @Override
    public PageInfo<Province> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Province> list = provinceMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<Province> list) {

    }

    @Override
    public void batchUpdate(List<Province> list) {

    }

    @Cacheable(value = "chinaRegionCache", key = "'provinces'")
    @Override
    public List<CascaderOptionDto> getCascaderOptionDtos() {
        return provinceMapper.getCascaderOptionDtos();
    }
}