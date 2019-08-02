
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.SideMenuMultiLanguageMapper;
import com.simon.model.SideMenuMultiLanguage;
import com.simon.repository.SideMenuMultiLanguageRepository;
import com.simon.service.SideMenuMultiLanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author jeesun
* @date 2019-05-30
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class SideMenuMultiLanguageServiceImpl extends CrudServiceImpl<SideMenuMultiLanguage, Long> implements SideMenuMultiLanguageService {
    @Autowired
    private SideMenuMultiLanguageMapper sideMenuMultiLanguageMapper;

    @Autowired
    private SideMenuMultiLanguageRepository sideMenuMultiLanguageRepository;

}