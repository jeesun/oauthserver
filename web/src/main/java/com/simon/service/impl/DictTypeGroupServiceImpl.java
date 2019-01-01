
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.dto.DictTypeDto;
import com.simon.dto.EasyUiTreeGridDto;
import com.simon.mapper.DictTypeGroupMapper;
import com.simon.model.DictType;
import com.simon.model.DictTypeGroup;
import com.simon.repository.DictTypeGroupRepository;
import com.simon.service.DictTypeGroupService;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public long count() {
        return dictTypeGroupRepository.count();
    }

    @Override
    public DictTypeGroup save(DictTypeGroup dictTypeGroup){
        return dictTypeGroupRepository.save(dictTypeGroup);
    }

    @Override
    public List<DictTypeGroup> save(List<DictTypeGroup> dictTypeGroupList) {
        return dictTypeGroupRepository.save(dictTypeGroupList);
    }

    @Override
    public PageInfo<DictTypeGroup> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }

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

    @Override
    public PageInfo<DictTypeGroup> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        var list = dictTypeGroupMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public List<DictTypeDto> getDtos(Integer limit, Integer offset) {
        List<DictTypeDto> dtoList = new ArrayList<>();
        /*List<DictTypeGroup> groups = dictTypeGroupRepository.findAll(new PageRequest((offset/limit - 1), limit, Sort.Direction.DESC, "id")).getContent();*/
        PageHelper.startPage(offset / limit + 1, limit);
        List<DictTypeGroup> list = dictTypeGroupMapper.getAll();
        PageInfo<DictTypeGroup> pageInfo = new PageInfo<>(list);
        List<DictTypeGroup> resultList = pageInfo.getList();
        for(int i = 0; i < resultList.size(); i++){
            DictTypeGroup dictTypeGroup = resultList.get(i);
            DictTypeDto dto = new DictTypeDto();
            dto.setId(String.valueOf(dictTypeGroup.getId()));
            dto.setName(dictTypeGroup.getTypeGroupName());
            dto.setCode(dictTypeGroup.getTypeGroupCode());
            //一级菜单
            dto.setType(1);
            dtoList.add(dto);

            List<DictType> dictTypes = dictTypeGroup.getDictTypes();
            if (null != dictTypes && dictTypes.size() > 0){
                for(int j = 0; j < dictTypes.size(); j++){
                    DictType dictType = dictTypes.get(j);
                    DictTypeDto subDto = new DictTypeDto();
                    subDto.setId(String.valueOf(dictTypeGroup.getId()) + "-" + String.valueOf(dictType.getId()));
                    subDto.setName(dictType.getTypeName());
                    subDto.setCode(dictType.getTypeCode());
                    subDto.setOrderNum(dictType.getOrderNum());
                    subDto.setPid(String.valueOf(dictTypeGroup.getId()));
                    //二级菜单
                    subDto.setType(2);

                    dtoList.add(subDto);
                }
            }
        }
        return dtoList;
    }

    @Override
    public PageInfo<EasyUiTreeGridDto> getTreeGridDtos(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }

        var list = dictTypeGroupMapper.getTreeGridDtos(params);
        return new PageInfo<>(list);
    }

    @Override
    public DictTypeGroup save(DictTypeDto dictTypeDto) {
        DictTypeGroup dictTypeGroup = new DictTypeGroup();
        if (StringUtils.isNotEmpty(dictTypeDto.getId())){
            dictTypeGroup.setId(Long.parseLong(dictTypeDto.getId()));
        }
        dictTypeGroup.setCreateDate(new Date());
        dictTypeGroup.setTypeGroupName(dictTypeDto.getName());
        dictTypeGroup.setTypeGroupCode(dictTypeDto.getCode());
        return dictTypeGroupRepository.save(dictTypeGroup);
    }

    @Override
    public int countByTypeGroupCode(String typeGroupCode) {
        return dictTypeGroupRepository.countByTypeGroupCode(typeGroupCode);
    }
}