package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.model.SideMenuAuthority;
import com.simon.provider.SideMenuAuthorityProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2019-01-14
**/
public interface SideMenuAuthorityMapper extends CrudMapper<SideMenuAuthority> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @SelectProvider(type = SideMenuAuthorityProvider.class, method = "getList")
    List<SideMenuAuthority> getList(Map<String, Object> map);

    int deleteByAuthorityAndNotIn(@Param("authority") String authority, @Param("sideMenuIds") String sideMenuIds);

    int deleteByEntityName(@Param("entityName") String entityName);

    /**
     * 删除所有指定菜单及其子菜单的权限记录
     * @param id 菜单id
     * @return 删除记录行数
     */
    int deleteBySideMenuIdIn(@Param("id") Long id);
}