
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.dto.DictTypeDto;
import com.simon.mapper.DictTypeMapper;
import com.simon.mapper.DictTypeMultiLanguageMapper;
import com.simon.model.DictType;
import com.simon.model.DictTypeMultiLanguage;
import com.simon.repository.DictTypeGroupRepository;
import com.simon.repository.DictTypeMultiLanguageRepository;
import com.simon.repository.DictTypeRepository;
import com.simon.service.DictTypeService;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-09-06 10:03:50
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictTypeRepository dictTypeRepository;

    @Autowired
    private DictTypeGroupRepository dictTypeGroupRepository;

    @Autowired
    private DictTypeMultiLanguageMapper dictTypeMultiLanguageMapper;

    @Autowired
    private DictTypeMultiLanguageRepository dictTypeMultiLanguageRepository;

    @Override
    public long count() {
        return dictTypeRepository.count();
    }

    @Override
    public DictType save(DictType dictType){
        return dictTypeRepository.save(dictType);
    }

    @Override
    public List<DictType> save(List<DictType> dictTypeList) {
        return dictTypeRepository.save(dictTypeList);
    }

    @Override
    public PageInfo<DictType> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<DictType> list = dictTypeMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<DictType> findAll(Pageable pageable){
        return dictTypeRepository.findAll(pageable);
    }

    @Override
    public List<DictType> findAll(){
        return dictTypeRepository.findAll();
    }

    @Override
    public void delete(Long id){
        dictTypeRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return dictTypeMapper.deleteByIds(ids);
    }

    @Override
    public DictType findById(Long id){
        return dictTypeRepository.findOne(id);
    }

    @Override
    public int insertList(List<DictType> list){
        return dictTypeMapper.insertList(list);
    }

    @Override
    public int insert(DictType dictType){
        return dictTypeMapper.insert(dictType);
    }

    @Override
    public int insertSelective(DictType dictType){
        return dictTypeMapper.insertSelective(dictType);
    }

    @Override
    public int updateByPrimaryKey(DictType dictType){
        return dictTypeMapper.updateByPrimaryKey(dictType);
    }

    @Override
    public int updateByPrimaryKeySelective(DictType dictType){
        return dictTypeMapper.updateByPrimaryKeySelective(dictType);
    }

    @Override
    public PageInfo<DictType> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        var list = dictTypeMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public List<DictType> getTypeByGroupCode(String groupCode, String language) {
        return dictTypeMapper.getByGroupCode(groupCode, language);
    }

    @Override
    public DictType save(DictTypeDto dictTypeDto, String language) {
        DictType dictType = new DictType();
        if(StringUtils.isNotEmpty(dictTypeDto.getId())){
            dictType.setId(Long.parseLong(dictTypeDto.getId()));
        }
        dictType.setCreateDate(LocalDateTime.now());
        dictType.setTypeGroupId(Long.parseLong(dictTypeDto.getPid()));
        dictType.setTypeGroupCode(dictTypeGroupRepository.findOne(Long.parseLong(dictTypeDto.getPid())).getTypeGroupCode());
        dictType.setTypeName(dictTypeDto.getName());
        dictType.setTypeCode(dictTypeDto.getCode());
        dictType.setOrderNum(dictTypeDto.getOrderNum());
        dictType = dictTypeRepository.save(dictType);

        DictTypeMultiLanguage dictTypeMultiLanguage = dictTypeMultiLanguageRepository.findByDictTypeIdAndLanguage(dictType.getId(), language);
        if (null == dictTypeMultiLanguage) {
            dictTypeMultiLanguage = new DictTypeMultiLanguage();
        }
        dictTypeMultiLanguage.setDictTypeId(dictType.getId());
        dictTypeMultiLanguage.setLanguage(language);
        dictTypeMultiLanguage.setName(dictTypeDto.getName());
        dictTypeMultiLanguageRepository.save(dictTypeMultiLanguage);

        return dictType;
    }

    @Override
    public DictTypeDto getDtoById(Long id, String language) {
        return dictTypeMapper.getDtoById(id, language);
    }
}