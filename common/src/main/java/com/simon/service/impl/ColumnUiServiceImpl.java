
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.ColumnUiMapper;
import com.simon.model.ColumnUi;
import com.simon.repository.ColumnUiRepository;
import com.simon.service.ColumnUiService;
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
* @date 2019-05-03
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class ColumnUiServiceImpl implements ColumnUiService {
    @Autowired
    private ColumnUiMapper columnUiMapper;

    @Autowired
    private ColumnUiRepository columnUiRepository;

    @Override
    public long count() {
        return columnUiRepository.count();
    }

    @Override
    public ColumnUi save(ColumnUi columnUi){
        return columnUiRepository.save(columnUi);
    }

    @Override
    public List<ColumnUi> save(List<ColumnUi> columnUiList) {
        return columnUiRepository.save(columnUiList);
    }

    @Override
    public PageInfo<ColumnUi> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<ColumnUi> list = columnUiMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<ColumnUi> findAll(Pageable pageable){
        return columnUiRepository.findAll(pageable);
    }

    @Override
    public List<ColumnUi> findAll(){
        return columnUiRepository.findAll();
    }

    @Override
    public void delete(Long id){
        columnUiRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return columnUiMapper.deleteByIds(ids);
    }

    @Override
    public ColumnUi findById(Long id){
        return columnUiRepository.findOne(id);
    }

    @Override
    public int insertList(List<ColumnUi> list){
        return columnUiMapper.insertList(list);
    }

    @Override
    public int insert(ColumnUi columnUi){
        return columnUiMapper.insert(columnUi);
    }

    @Override
    public int insertSelective(ColumnUi columnUi){
        return columnUiMapper.insertSelective(columnUi);
    }

    @Override
    public int updateByPrimaryKey(ColumnUi columnUi){
        return columnUiMapper.updateByPrimaryKey(columnUi);
    }

    @Override
    public int updateByPrimaryKeySelective(ColumnUi columnUi){
        return columnUiMapper.updateByPrimaryKeySelective(columnUi);
    }

    @Override
    public PageInfo<ColumnUi> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<ColumnUi> list = columnUiMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<ColumnUi> list) {

    }

    @Override
    public void batchUpdate(List<ColumnUi> list) {

    }

    @Override
    public List<ColumnUi> findByTableName(String tableName) {
        return columnUiRepository.findByTableName(tableName);
    }
}