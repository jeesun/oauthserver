
package com.simon.service.impl;

import com.simon.mapper.NewsInfoMapper;
import com.simon.model.NewsInfo;
import com.simon.service.NewsInfoService;
import com.simon.repository.NewsInfoRepository;
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

/**
* @author SimonSun
* @date 2019-01-20
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class NewsInfoServiceImpl implements NewsInfoService {
    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Autowired
    private NewsInfoRepository newsInfoRepository;

    @Override
    public long count() {
        return newsInfoRepository.count();
    }

    @Override
    public NewsInfo save(NewsInfo newsInfo){
        return newsInfoRepository.save(newsInfo);
    }

    @Override
    public List<NewsInfo> save(List<NewsInfo> newsInfoList) {
        return newsInfoRepository.save(newsInfoList);
    }

    @Override
    public PageInfo<NewsInfo> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<NewsInfo> list = newsInfoMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<NewsInfo> findAll(Pageable pageable){
        return newsInfoRepository.findAll(pageable);
    }

    @Override
    public List<NewsInfo> findAll(){
        return newsInfoRepository.findAll();
    }

    @Override
    public void delete(Long id){
        newsInfoRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return newsInfoMapper.deleteByIds(ids);
    }

    @Override
    public NewsInfo findById(Long id){
        return newsInfoRepository.findOne(id);
    }

    @Override
    public int insertList(List<NewsInfo> list){
        return newsInfoMapper.insertList(list);
    }

    @Override
    public int insert(NewsInfo newsInfo){
        return newsInfoMapper.insert(newsInfo);
    }

    @Override
    public int insertSelective(NewsInfo newsInfo){
        return newsInfoMapper.insertSelective(newsInfo);
    }

    @Override
    public int updateByPrimaryKey(NewsInfo newsInfo){
        return newsInfoMapper.updateByPrimaryKey(newsInfo);
    }

    @Override
    public int updateByPrimaryKeySelective(NewsInfo newsInfo){
        return newsInfoMapper.updateByPrimaryKeySelective(newsInfo);
    }

    @Override
    public PageInfo<NewsInfo> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<NewsInfo> list = newsInfoMapper.getList(params);
        return new PageInfo<>(list);
    }
}