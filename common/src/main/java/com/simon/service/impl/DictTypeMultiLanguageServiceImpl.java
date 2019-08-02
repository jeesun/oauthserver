
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.DictTypeMultiLanguageMapper;
import com.simon.model.DictTypeMultiLanguage;
import com.simon.repository.DictTypeMultiLanguageRepository;
import com.simon.service.DictTypeMultiLanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author jeesun
* @date 2019-06-03
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class DictTypeMultiLanguageServiceImpl extends CrudServiceImpl<DictTypeMultiLanguage, Long> implements DictTypeMultiLanguageService {
    @Autowired
    private DictTypeMultiLanguageMapper dictTypeMultiLanguageMapper;

    @Autowired
    private DictTypeMultiLanguageRepository dictTypeMultiLanguageRepository;

}