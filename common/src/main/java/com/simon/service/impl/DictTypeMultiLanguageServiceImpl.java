
package com.simon.service.impl;

import com.simon.mapper.DictTypeMultiLanguageMapper;
import com.simon.model.DictTypeMultiLanguage;
import com.simon.service.DictTypeMultiLanguageService;
import com.simon.repository.DictTypeMultiLanguageRepository;
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
public class DictTypeMultiLanguageServiceImpl implements DictTypeMultiLanguageService {
    @Autowired
    private DictTypeMultiLanguageMapper dictTypeMultiLanguageMapper;

    @Autowired
    private DictTypeMultiLanguageRepository dictTypeMultiLanguageRepository;

    @Override
    public long count() {
        return dictTypeMultiLanguageRepository.count();
    }

    @Override
    public DictTypeMultiLanguage save(DictTypeMultiLanguage dictTypeMultiLanguage){
        return dictTypeMultiLanguageRepository.save(dictTypeMultiLanguage);
    }

    @Override
    public List<DictTypeMultiLanguage> save(List<DictTypeMultiLanguage> dictTypeMultiLanguageList) {
        return dictTypeMultiLanguageRepository.save(dictTypeMultiLanguageList);
    }

    @Override
    public PageInfo<DictTypeMultiLanguage> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<DictTypeMultiLanguage> list = dictTypeMultiLanguageMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<DictTypeMultiLanguage> findAll(Pageable pageable){
        return dictTypeMultiLanguageRepository.findAll(pageable);
    }

    @Override
    public List<DictTypeMultiLanguage> findAll(){
        return dictTypeMultiLanguageRepository.findAll();
    }

    @Override
    public void delete(Long id){
        dictTypeMultiLanguageRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return dictTypeMultiLanguageMapper.deleteByIds(ids);
    }

    @Override
    public DictTypeMultiLanguage findById(Long id){
        return dictTypeMultiLanguageRepository.findOne(id);
    }

    @Override
    public int insertList(List<DictTypeMultiLanguage> list){
        return dictTypeMultiLanguageMapper.insertList(list);
    }

    @Override
    public int insert(DictTypeMultiLanguage dictTypeMultiLanguage){
        return dictTypeMultiLanguageMapper.insert(dictTypeMultiLanguage);
    }

    @Override
    public int insertSelective(DictTypeMultiLanguage dictTypeMultiLanguage){
        return dictTypeMultiLanguageMapper.insertSelective(dictTypeMultiLanguage);
    }

    @Override
    public int updateByPrimaryKey(DictTypeMultiLanguage dictTypeMultiLanguage){
        return dictTypeMultiLanguageMapper.updateByPrimaryKey(dictTypeMultiLanguage);
    }

    @Override
    public int updateByPrimaryKeySelective(DictTypeMultiLanguage dictTypeMultiLanguage){
        return dictTypeMultiLanguageMapper.updateByPrimaryKeySelective(dictTypeMultiLanguage);
    }

    @Override
    public PageInfo<DictTypeMultiLanguage> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<DictTypeMultiLanguage> list = dictTypeMultiLanguageMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<DictTypeMultiLanguage> list) {

    }

    @Override
    public void batchUpdate(List<DictTypeMultiLanguage> list) {

    }
}