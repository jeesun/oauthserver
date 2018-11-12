
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.NewsTagMapper;
import com.simon.model.NewsTag;
import com.simon.repository.NewsTagRepository;
import com.simon.service.NewsTagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author SimonSun
* @create 2018-08-06 20:56:26
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class NewsTagServiceImpl implements NewsTagService {
    @Autowired
    private NewsTagMapper newsTagMapper;

    @Autowired
    private NewsTagRepository newsTagRepository;

    @Override
    public long count() {
        return newsTagRepository.count();
    }

    @Override
    public NewsTag save(NewsTag newsTag){
        return newsTagRepository.save(newsTag);
    }

    @Override
    public List<NewsTag> save(List<NewsTag> newsTagList) {
        return newsTagRepository.save(newsTagList);
    }

    @Override
    public PageInfo<NewsTag> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<NewsTag> list = newsTagMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<NewsTag> findAll(Pageable pageable){
        return newsTagRepository.findAll(pageable);
    }

    @Override
    public List<NewsTag> findAll(){
        return newsTagRepository.findAll();
    }

    @Override
    public void delete(Long id){
        newsTagRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return newsTagMapper.deleteByIds(ids);
    }

    @Override
    public NewsTag findById(Long id){
        return newsTagRepository.findOne(id);
    }

    @Override
    public int insertList(List<NewsTag> list){
        return newsTagMapper.insertList(list);
    }

    @Override
    public int insert(NewsTag newsTag){
        return newsTagMapper.insert(newsTag);
    }

    @Override
    public int insertSelective(NewsTag newsTag){
        return newsTagMapper.insertSelective(newsTag);
    }

    @Override
    public int updateByPrimaryKey(NewsTag newsTag) {
        return newsTagMapper.updateByPrimaryKey(newsTag);
    }

    @Override
    public int updateByPrimaryKeySelective(NewsTag newsTag) {
        return newsTagMapper.updateByPrimaryKeySelective(newsTag);
    }
}