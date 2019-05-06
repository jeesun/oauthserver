
package com.simon.service.impl;

import com.simon.mapper.VillageMapper;
import com.simon.model.Village;
import com.simon.service.VillageService;
import com.simon.repository.VillageRepository;
import com.simon.common.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-24
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class VillageServiceImpl implements VillageService {
    @Autowired
    private VillageMapper villageMapper;

    @Autowired
    private VillageRepository villageRepository;

    @Override
    public long count() {
        return villageRepository.count();
    }

    @Override
    public Village save(Village village){
        return villageRepository.save(village);
    }

    @Override
    public List<Village> save(List<Village> villageList) {
        return villageRepository.save(villageList);
    }

    @Override
    public PageInfo<Village> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Village> list = villageMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<Village> findAll(Pageable pageable){
        return villageRepository.findAll(pageable);
    }

    @Override
    public List<Village> findAll(){
        return villageRepository.findAll();
    }

    @Override
    public void delete(Integer id){
        villageRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return villageMapper.deleteByIds(ids);
    }

    @Override
    public Village findById(Integer id){
        return villageRepository.findOne(id);
    }

    @Override
    public int insertList(List<Village> list){
        return villageMapper.insertList(list);
    }

    @Override
    public int insert(Village village){
        return villageMapper.insert(village);
    }

    @Override
    public int insertSelective(Village village){
        return villageMapper.insertSelective(village);
    }

    @Override
    public int updateByPrimaryKey(Village village){
        return villageMapper.updateByPrimaryKey(village);
    }

    @Override
    public int updateByPrimaryKeySelective(Village village){
        return villageMapper.updateByPrimaryKeySelective(village);
    }

    @Override
    public PageInfo<Village> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Village> list = villageMapper.getList(params);
        return new PageInfo<>(list);
    }
}