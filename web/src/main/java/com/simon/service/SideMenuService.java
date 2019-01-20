package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.dto.ButtonAuthorityDto;
import com.simon.dto.EasyUiTreeDto;
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

    /**
     * 根据请求地址查询权限组
     * @param url 请求地址
     * @param requestMethod 请求方法
     * @return 权限组
     */
    String findAuthorityByUrlAndRequestMethod(String url, String requestMethod);

    /**
     * 代码生成时，向t_side_menu表添加访问权限数据
     * @param entityName 实体类名
     * @param pid 父菜单id
     * @param allowedRoles 允许访问的角色，多个逗号隔开
     * @param tableComment 表注释
     * @return 影响行数
     */
    int insertOrUpdateByEntityName(String entityName, Long pid, String allowedRoles, String tableComment);

    /**
     * 根据实体类名查询按钮权限
     * @param entityName 实体类名
     * @return 结果List
     */
    List<ButtonAuthorityDto> findButtonAuthorityDtoByEntityName(String entityName);

    List<EasyUiTreeDto> getAuth(String typeCode);

    int updateAuth(String ids);
}