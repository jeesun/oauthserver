
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.TownMapper;
import com.simon.model.Town;
import com.simon.repository.TownRepository;
import com.simon.service.TownService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author SimonSun
* @date 2019-04-25
**/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class TownServiceImpl extends CrudServiceImpl<Town, Integer> implements TownService {
    @Autowired
    private TownMapper townMapper;

    @Autowired
    private TownRepository townRepository;
}