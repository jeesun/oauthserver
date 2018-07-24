package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.model.NewsInfo;

/**
 * 新闻
 *
 * @author simon
 * @create 2018-07-24 0:23
 **/

public interface NewsInfoService {
    PageInfo<NewsInfo> getAll(int pageNo);
}
