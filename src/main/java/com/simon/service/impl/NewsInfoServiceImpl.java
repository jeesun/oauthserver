
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.NewsInfoMapper;
import com.simon.model.NewsInfo;
import com.simon.repository.NewsInfoRepository;
import com.simon.service.NewsInfoService;
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
@Transactional
public class NewsInfoServiceImpl implements NewsInfoService {
    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Autowired
    private NewsInfoRepository newsInfoRepository;

    @Override
    public NewsInfo save(NewsInfo newsInfo){
        return newsInfoRepository.save(newsInfo);
    }

    @Override
    public List<NewsInfo> save(List<NewsInfo> newsInfoList) {
        return newsInfoRepository.save(newsInfoList);
    }

    @Override
    public PageInfo<NewsInfo> findAll(int pageNo){
        PageHelper.startPage(pageNo, AppConfig.DEFAULT_PAGE_SIZE);
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
    public int updateByPrimaryKey(NewsInfo newsInfo) {
        return newsInfoMapper.updateByPrimaryKey(newsInfo);
    }

    @Override
    public int updateByPrimaryKeySelective(NewsInfo newsInfo) {
        return newsInfoMapper.updateByPrimaryKeySelective(newsInfo);
    }
}