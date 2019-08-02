
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.NewsTagMapper;
import com.simon.model.NewsTag;
import com.simon.repository.NewsTagRepository;
import com.simon.service.NewsTagService;
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
* @create 2018-08-06 20:56:26
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class NewsTagServiceImpl extends CrudServiceImpl<NewsTag, Long> implements NewsTagService {
    @Autowired
    private NewsTagMapper newsTagMapper;

    @Autowired
    private NewsTagRepository newsTagRepository;

}