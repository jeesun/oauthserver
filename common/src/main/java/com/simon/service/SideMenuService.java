package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.dto.ButtonAuthorityDto;
import com.simon.dto.EasyUiTreeDto;
import com.simon.dto.SideMenuDto;
import com.simon.model.SideMenu;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-09-26
**/
public interface SideMenuService extends BasicService<SideMenu, Long> {
    PageInfo<SideMenu> getAll(Map<String, Object> params, Integer limit, Integer offset);
    List<SideMenu> getAll(String language);

    /**
     * 根据请求地址查询权限组
     * @param url 请求地址
     * @param requestMethod 请求方法
     * @return 权限组
     */
    String findAuthorityByUrlAndRequestMethod(String url, String requestMethod);

    /**
     * 根据实体类名查询按钮权限
     * @param entityName 实体类名
     * @return 结果List
     */
    List<ButtonAuthorityDto> findButtonAuthorityDtoByEntityName(String entityName);

    /**
     * 获取树形结构数据
     * @param typeCode 角色类型
     * @param language 语言
     * @return 树形结构数据
     */
    List<EasyUiTreeDto> getAuth(String typeCode, String language);

    /**
     * 获取一级菜单
     * @param language 语言
     * @return 一级菜单列表
     */
    List<SideMenuDto> getLevel1(String language);

    /**
     * 获取子菜单列表
     * @param pid 父菜单id
     * @return 子菜单列表
     */
    List<SideMenu> selectByPid(Long pid, String language);

    SideMenu findById(Long id, String language);

    int updateByPrimaryKeySelective(SideMenu sideMenu, String language);

    int save(SideMenu sideMenu, String language);
}