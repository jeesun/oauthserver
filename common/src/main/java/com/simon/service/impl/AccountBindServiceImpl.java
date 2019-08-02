
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.AccountBindMapper;
import com.simon.model.AccountBind;
import com.simon.repository.AccountBindRepository;
import com.simon.service.AccountBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author SimonSun
* @date 2018-12-04
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class AccountBindServiceImpl extends CrudServiceImpl<AccountBind, Long> implements AccountBindService {
    @Autowired
    private AccountBindMapper accountBindMapper;

    @Autowired
    private AccountBindRepository accountBindRepository;

}