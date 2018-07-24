package com.simon.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.mapper.NewsInfoMapper;
import com.simon.model.NewsInfo;
import com.simon.service.NewsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public PageInfo<NewsInfo> getAll(int pageNo) {
        PageHelper.startPage(pageNo, 2);
        List<NewsInfo> list = newsInfoMapper.selectAll();
        return new PageInfo<>(list);
    }
}
