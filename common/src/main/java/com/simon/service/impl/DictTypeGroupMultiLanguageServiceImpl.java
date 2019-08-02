
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.DictTypeGroupMultiLanguageMapper;
import com.simon.model.DictTypeGroupMultiLanguage;
import com.simon.repository.DictTypeGroupMultiLanguageRepository;
import com.simon.service.DictTypeGroupMultiLanguageService;
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
public class DictTypeGroupMultiLanguageServiceImpl extends CrudServiceImpl<DictTypeGroupMultiLanguage, Long> implements DictTypeGroupMultiLanguageService {
    @Autowired
    private DictTypeGroupMultiLanguageMapper dictTypeGroupMultiLanguageMapper;

    @Autowired
    private DictTypeGroupMultiLanguageRepository dictTypeGroupMultiLanguageRepository;

}