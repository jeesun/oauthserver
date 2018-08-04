package com.simon.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.mapper.NewsInfoMapper;
import com.simon.model.NewsInfo;
import com.simon.repository.NewsInfoRepository;
import com.simon.service.NewsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻
 *
 * @author simon
 * @create 2018-07-24 0:24
 **/
@Slf4j
@Service
public class NewsInfoServiceImpl implements NewsInfoService {
    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Autowired
    private NewsInfoRepository newsInfoRepository;

    @Override
    public PageInfo<NewsInfo> getAll(int pageNo) {
        PageHelper.startPage(pageNo, 2);
        List<NewsInfo> list = newsInfoMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<NewsInfo> getAll(Pageable pageable) {
        return newsInfoRepository.findAll(pageable);
    }
}
