
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.DictTypeGroupMapper;
import com.simon.model.DictTypeGroup;
import com.simon.repository.DictTypeGroupRepository;
import com.simon.service.DictTypeGroupService;
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
public class DictTypeGroupServiceImpl implements DictTypeGroupService {
    @Autowired
    private DictTypeGroupMapper dictTypeGroupMapper;

    @Autowired
    private DictTypeGroupRepository dictTypeGroupRepository;

    @Override
    public DictTypeGroup save(DictTypeGroup dictTypeGroup){
        return dictTypeGroupRepository.save(dictTypeGroup);
    }

    @Override
    public List<DictTypeGroup> save(List<DictTypeGroup> dictTypeGroupList) {
        return dictTypeGroupRepository.save(dictTypeGroupList);
    }

    @Override
    public PageInfo<DictTypeGroup> findAll(int pageNo){
        PageHelper.startPage(pageNo, AppConfig.DEFAULT_PAGE_SIZE);
        List<DictTypeGroup> list = dictTypeGroupMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<DictTypeGroup> findAll(Pageable pageable){
        return dictTypeGroupRepository.findAll(pageable);
    }

    @Override
    public List<DictTypeGroup> findAll(){
        return dictTypeGroupRepository.findAll();
    }

    @Override
    public void delete(Long id){
        dictTypeGroupRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return dictTypeGroupMapper.deleteByIds(ids);
    }

    @Override
    public DictTypeGroup findById(Long id){
        return dictTypeGroupRepository.findOne(id);
    }

    @Override
    public int insertList(List<DictTypeGroup> list){
        return dictTypeGroupMapper.insertList(list);
    }

    @Override
    public int insert(DictTypeGroup dictTypeGroup){
        return dictTypeGroupMapper.insert(dictTypeGroup);
    }

    @Override
    public int insertSelective(DictTypeGroup dictTypeGroup){
        return dictTypeGroupMapper.insertSelective(dictTypeGroup);
    }

    @Override
    public int updateByPrimaryKey(DictTypeGroup dictTypeGroup){
        return dictTypeGroupMapper.updateByPrimaryKey(dictTypeGroup);
    }

    @Override
    public int updateByPrimaryKeySelective(DictTypeGroup dictTypeGroup){
        return dictTypeGroupMapper.updateByPrimaryKeySelective(dictTypeGroup);
    }
}