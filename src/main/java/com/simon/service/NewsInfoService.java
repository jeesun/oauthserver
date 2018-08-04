package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.model.NewsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 新闻
 *
 * @author simon
 * @create 2018-07-24 0:23
 **/

public interface NewsInfoService {
    PageInfo<NewsInfo> getAll(int pageNo);
    Page<NewsInfo> getAll(Pageable pageable);
}
