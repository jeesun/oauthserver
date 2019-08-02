package com.simon.service;

import com.simon.common.service.CrudService;
import com.simon.model.SideMenuAuthority;

import java.util.List;

/**
* @author SimonSun
* @date 2019-01-14
**/
public interface SideMenuAuthorityService extends CrudService<SideMenuAuthority, Long> {
    /**
     * 更新角色权限（先删除再创建）
     * @param sideMenuIds 菜单id
     * @param authority 权限，如ROLE_USER
     */
    void updateAuth(List<Long> sideMenuIds, String authority);
}