
package com.simon.service.impl;

import com.simon.mapper.DictTypeGroupMultiLanguageMapper;
import com.simon.model.DictTypeGroupMultiLanguage;
import com.simon.service.DictTypeGroupMultiLanguageService;
import com.simon.repository.DictTypeGroupMultiLanguageRepository;
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
* @date 2019-06-03
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class DictTypeGroupMultiLanguageServiceImpl implements DictTypeGroupMultiLanguageService {
    @Autowired
    private DictTypeGroupMultiLanguageMapper dictTypeGroupMultiLanguageMapper;

    @Autowired
    private DictTypeGroupMultiLanguageRepository dictTypeGroupMultiLanguageRepository;

    @Override
    public long count() {
        return dictTypeGroupMultiLanguageRepository.count();
    }

    @Override
    public DictTypeGroupMultiLanguage save(DictTypeGroupMultiLanguage dictTypeGroupMultiLanguage){
        return dictTypeGroupMultiLanguageRepository.save(dictTypeGroupMultiLanguage);
    }

    @Override
    public List<DictTypeGroupMultiLanguage> save(List<DictTypeGroupMultiLanguage> dictTypeGroupMultiLanguageList) {
        return dictTypeGroupMultiLanguageRepository.save(dictTypeGroupMultiLanguageList);
    }

    @Override
    public PageInfo<DictTypeGroupMultiLanguage> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<DictTypeGroupMultiLanguage> list = dictTypeGroupMultiLanguageMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<DictTypeGroupMultiLanguage> findAll(Pageable pageable){
        return dictTypeGroupMultiLanguageRepository.findAll(pageable);
    }

    @Override
    public List<DictTypeGroupMultiLanguage> findAll(){
        return dictTypeGroupMultiLanguageRepository.findAll();
    }

    @Override
    public void delete(Long id){
        dictTypeGroupMultiLanguageRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return dictTypeGroupMultiLanguageMapper.deleteByIds(ids);
    }

    @Override
    public DictTypeGroupMultiLanguage findById(Long id){
        return dictTypeGroupMultiLanguageRepository.findOne(id);
    }

    @Override
    public int insertList(List<DictTypeGroupMultiLanguage> list){
        return dictTypeGroupMultiLanguageMapper.insertList(list);
    }

    @Override
    public int insert(DictTypeGroupMultiLanguage dictTypeGroupMultiLanguage){
        return dictTypeGroupMultiLanguageMapper.insert(dictTypeGroupMultiLanguage);
    }

    @Override
    public int insertSelective(DictTypeGroupMultiLanguage dictTypeGroupMultiLanguage){
        return dictTypeGroupMultiLanguageMapper.insertSelective(dictTypeGroupMultiLanguage);
    }

    @Override
    public int updateByPrimaryKey(DictTypeGroupMultiLanguage dictTypeGroupMultiLanguage){
        return dictTypeGroupMultiLanguageMapper.updateByPrimaryKey(dictTypeGroupMultiLanguage);
    }

    @Override
    public int updateByPrimaryKeySelective(DictTypeGroupMultiLanguage dictTypeGroupMultiLanguage){
        return dictTypeGroupMultiLanguageMapper.updateByPrimaryKeySelective(dictTypeGroupMultiLanguage);
    }

    @Override
    public PageInfo<DictTypeGroupMultiLanguage> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<DictTypeGroupMultiLanguage> list = dictTypeGroupMultiLanguageMapper.getList(params);
        return new PageInfo<>(list);
    }
}