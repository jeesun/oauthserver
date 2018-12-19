package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.model.SideMenu;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-09-26
**/
public interface SideMenuService extends BasicService<SideMenu, Long> {
    PageInfo<SideMenu> getAll(Map<String, Object> params, Integer limit, Integer offset);
    List<SideMenu> getAll();
    PageInfo<SideMenu> getList(Map<String, Object> params, Integer limit, Integer offset, String orderBy);
}