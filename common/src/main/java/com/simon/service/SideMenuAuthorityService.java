package com.simon.service;

import com.simon.common.service.BasicService;
import com.simon.model.SideMenuAuthority;

import java.util.List;

/**
* @author SimonSun
* @date 2019-01-14
**/
public interface SideMenuAuthorityService extends BasicService<SideMenuAuthority, Long> {
    /**
     * 更新角色权限（先删除再创建）
     * @param sideMenuIds 菜单id
     * @param authority 权限，如ROLE_USER
     */
    void updateAuth(List<Long> sideMenuIds, String authority);

    /**
     * 批量保存
     * @param list
     */
    void batchSave(List<SideMenuAuthority> list);

    /**
     * 批量更新
     * @param list
     */
    void batchUpdate(List<SideMenuAuthority> list);
}