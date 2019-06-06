
package com.simon.service.impl;

import com.simon.mapper.SideMenuMultiLanguageMapper;
import com.simon.model.SideMenuMultiLanguage;
import com.simon.service.SideMenuMultiLanguageService;
import com.simon.repository.SideMenuMultiLanguageRepository;
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
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* @author jeesun
* @date 2019-05-30
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class SideMenuMultiLanguageServiceImpl implements SideMenuMultiLanguageService {
    @Autowired
    private SideMenuMultiLanguageMapper sideMenuMultiLanguageMapper;

    @Autowired
    private SideMenuMultiLanguageRepository sideMenuMultiLanguageRepository;

    @Override
    public long count() {
        return sideMenuMultiLanguageRepository.count();
    }

    @Override
    public SideMenuMultiLanguage save(SideMenuMultiLanguage sideMenuMultiLanguage){
        return sideMenuMultiLanguageRepository.save(sideMenuMultiLanguage);
    }

    @Override
    public List<SideMenuMultiLanguage> save(List<SideMenuMultiLanguage> sideMenuMultiLanguageList) {
        return sideMenuMultiLanguageRepository.save(sideMenuMultiLanguageList);
    }

    @Override
    public PageInfo<SideMenuMultiLanguage> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<SideMenuMultiLanguage> list = sideMenuMultiLanguageMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<SideMenuMultiLanguage> findAll(Pageable pageable){
        return sideMenuMultiLanguageRepository.findAll(pageable);
    }

    @Override
    public List<SideMenuMultiLanguage> findAll(){
        return sideMenuMultiLanguageRepository.findAll();
    }

    @Override
    public void delete(Long id){
        sideMenuMultiLanguageRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return sideMenuMultiLanguageMapper.deleteByIds(ids);
    }

    @Override
    public SideMenuMultiLanguage findById(Long id){
        return sideMenuMultiLanguageRepository.findOne(id);
    }

    @Override
    public int insertList(List<SideMenuMultiLanguage> list){
        return sideMenuMultiLanguageMapper.insertList(list);
    }

    @Override
    public int insert(SideMenuMultiLanguage sideMenuMultiLanguage){
        return sideMenuMultiLanguageMapper.insert(sideMenuMultiLanguage);
    }

    @Override
    public int insertSelective(SideMenuMultiLanguage sideMenuMultiLanguage){
        return sideMenuMultiLanguageMapper.insertSelective(sideMenuMultiLanguage);
    }

    @Override
    public int updateByPrimaryKey(SideMenuMultiLanguage sideMenuMultiLanguage){
        return sideMenuMultiLanguageMapper.updateByPrimaryKey(sideMenuMultiLanguage);
    }

    @Override
    public int updateByPrimaryKeySelective(SideMenuMultiLanguage sideMenuMultiLanguage){
        return sideMenuMultiLanguageMapper.updateByPrimaryKeySelective(sideMenuMultiLanguage);
    }

    @Override
    public PageInfo<SideMenuMultiLanguage> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<SideMenuMultiLanguage> list = sideMenuMultiLanguageMapper.getList(params);
        return new PageInfo<>(list);
    }
}