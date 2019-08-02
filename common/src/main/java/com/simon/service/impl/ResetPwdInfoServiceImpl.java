
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.ResetPwdInfoMapper;
import com.simon.model.ResetPwdInfo;
import com.simon.repository.ResetPwdInfoRepository;
import com.simon.service.ResetPwdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author SimonSun
* @create 2018-08-06 20:56:26
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class ResetPwdInfoServiceImpl extends CrudServiceImpl<ResetPwdInfo, Long> implements ResetPwdInfoService {
    @Autowired
    private ResetPwdInfoMapper resetPwdInfoMapper;

    @Autowired
    private ResetPwdInfoRepository resetPwdInfoRepository;

}