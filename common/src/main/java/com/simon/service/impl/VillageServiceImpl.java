
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.VillageMapper;
import com.simon.model.Village;
import com.simon.repository.VillageRepository;
import com.simon.service.VillageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author SimonSun
* @date 2019-04-24
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class VillageServiceImpl extends CrudServiceImpl<Village, Integer> implements VillageService {
    @Autowired
    private VillageMapper villageMapper;

    @Autowired
    private VillageRepository villageRepository;

}