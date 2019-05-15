package com.simon.service;

import com.simon.common.service.BasicService;
import com.simon.model.SideMenuAuthority;

/**
* @author SimonSun
* @date 2019-01-14
**/
public interface SideMenuAuthorityService extends BasicService<SideMenuAuthority, Long> {
    void updateAuth(String sideMenuIds, String authority);
}