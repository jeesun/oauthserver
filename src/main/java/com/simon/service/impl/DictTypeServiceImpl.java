
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.DictTypeMapper;
import com.simon.model.DictType;
import com.simon.repository.DictTypeRepository;
import com.simon.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public DictType save(DictType dictType){
        return dictTypeRepository.save(dictType);
    }

    @Override
    public List<DictType> save(List<DictType> dictTypeList) {
        return dictTypeRepository.save(dictTypeList);
    }

    @Override
    public PageInfo<DictType> findAll(int pageNo){
        PageHelper.startPage(pageNo, AppConfig.DEFAULT_PAGE_SIZE);
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
    public List<DictType> getTypeByGroupCode(String groupCode) {
        return dictTypeMapper.getByGroupCode(groupCode);
    }
}