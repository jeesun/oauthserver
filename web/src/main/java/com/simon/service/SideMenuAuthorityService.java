package com.simon.service;
import com.simon.model.SideMenuAuthority;
import com.simon.common.service.BasicService;

/**
* @author SimonSun
* @date 2019-01-14
**/
public interface SideMenuAuthorityService extends BasicService<SideMenuAuthority, Long> {
    void updateAuth(String sideMenuIds, String authority);
}