
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.LoggingEventMapper;
import com.simon.model.LoggingEvent;
import com.simon.repository.LoggingEventRepository;
import com.simon.service.LoggingEventService;
import lombok.var;
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
* @date 2018-11-09
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class LoggingEventServiceImpl implements LoggingEventService {
    @Autowired
    private LoggingEventMapper loggingEventMapper;

    @Autowired
    private LoggingEventRepository loggingEventRepository;

    @Override
    public long count() {
        return loggingEventRepository.count();
    }

    @Override
    public LoggingEvent save(LoggingEvent loggingEvent){
        return loggingEventRepository.save(loggingEvent);
    }

    @Override
    public List<LoggingEvent> save(List<LoggingEvent> loggingEventList) {
        return loggingEventRepository.save(loggingEventList);
    }

    @Override
    public PageInfo<LoggingEvent> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<LoggingEvent> list = loggingEventMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<LoggingEvent> findAll(Pageable pageable){
        return loggingEventRepository.findAll(pageable);
    }

    @Override
    public List<LoggingEvent> findAll(){
        return loggingEventRepository.findAll();
    }

    @Override
    public void delete(Long id){
        loggingEventRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return loggingEventMapper.deleteByIds(ids);
    }

    @Override
    public LoggingEvent findById(Long id){
        return loggingEventRepository.findOne(id);
    }

    @Override
    public int insertList(List<LoggingEvent> list){
        return loggingEventMapper.insertList(list);
    }

    @Override
    public int insert(LoggingEvent loggingEvent){
        return loggingEventMapper.insert(loggingEvent);
    }

    @Override
    public int insertSelective(LoggingEvent loggingEvent){
        return loggingEventMapper.insertSelective(loggingEvent);
    }

    @Override
    public int updateByPrimaryKey(LoggingEvent loggingEvent){
        return loggingEventMapper.updateByPrimaryKey(loggingEvent);
    }

    @Override
    public int updateByPrimaryKeySelective(LoggingEvent loggingEvent){
        return loggingEventMapper.updateByPrimaryKeySelective(loggingEvent);
    }

    @Override
    public PageInfo<LoggingEvent> getList(Map<String, Object> params, Integer limit, Integer offset, String orderBy) {
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(offset/limit + 1, limit);
        }else{
            PageHelper.startPage(offset/limit + 1, limit, orderBy);
        }

        var list = loggingEventMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<LoggingEvent> list) {

    }

    @Override
    public void batchUpdate(List<LoggingEvent> list) {

    }

    @Override
    public void deleteAll() {
        loggingEventRepository.deleteAll();
    }
}