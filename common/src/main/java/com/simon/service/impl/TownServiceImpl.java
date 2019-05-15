
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.TownMapper;
import com.simon.model.Town;
import com.simon.repository.TownRepository;
import com.simon.service.TownService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-04-25
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class TownServiceImpl implements TownService {
    @Autowired
    private TownMapper townMapper;

    @Autowired
    private TownRepository townRepository;

    @Override
    public long count() {
        return townRepository.count();
    }

    @Override
    public Town save(Town town){
        return townRepository.save(town);
    }

    @Override
    public List<Town> save(List<Town> townList) {
        return townRepository.save(townList);
    }

    @Override
    public PageInfo<Town> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Town> list = townMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<Town> findAll(Pageable pageable){
        return townRepository.findAll(pageable);
    }

    @Override
    public List<Town> findAll(){
        return townRepository.findAll();
    }

    @Override
    public void delete(Integer id){
        townRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return townMapper.deleteByIds(ids);
    }

    @Override
    public Town findById(Integer id){
        return townRepository.findOne(id);
    }

    @Override
    public int insertList(List<Town> list){
        return townMapper.insertList(list);
    }

    @Override
    public int insert(Town town){
        return townMapper.insert(town);
    }

    @Override
    public int insertSelective(Town town){
        return townMapper.insertSelective(town);
    }

    @Override
    public int updateByPrimaryKey(Town town){
        return townMapper.updateByPrimaryKey(town);
    }

    @Override
    public int updateByPrimaryKeySelective(Town town){
        return townMapper.updateByPrimaryKeySelective(town);
    }

    @Override
    public PageInfo<Town> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Town> list = townMapper.getList(params);
        return new PageInfo<>(list);
    }
}