
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.NoticeMsgMapper;
import com.simon.model.NoticeMsg;
import com.simon.repository.NoticeMsgRepository;
import com.simon.service.NoticeMsgService;
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
* @date 2018-11-24
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class NoticeMsgServiceImpl extends CrudServiceImpl<NoticeMsg, Long> implements NoticeMsgService {
    @Autowired
    private NoticeMsgMapper noticeMsgMapper;

    @Autowired
    private NoticeMsgRepository noticeMsgRepository;
}