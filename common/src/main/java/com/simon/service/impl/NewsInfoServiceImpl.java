
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.NewsInfoMapper;
import com.simon.model.NewsInfo;
import com.simon.repository.NewsInfoRepository;
import com.simon.service.NewsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author SimonSun
* @date 2019-01-20
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class NewsInfoServiceImpl extends CrudServiceImpl<NewsInfo, Long> implements NewsInfoService {
    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Autowired
    private NewsInfoRepository newsInfoRepository;

}