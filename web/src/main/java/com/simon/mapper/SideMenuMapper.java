package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.ButtonAuthorityDto;
import com.simon.dto.EasyUiTreeDto;
import com.simon.dto.SideMenuDto;
import com.simon.model.SideMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SideMenuMapper extends MyMapper<SideMenu> {
    List<SideMenu> selectByPid(@Param("pid") Long pid);
    List<SideMenu> selectByPids(@Param("pids") String pids);

    /**
     * 查询子菜单列表
     * @param pidArray 一级菜单id
     * @return 子菜单列表
     */
    List<SideMenu> selectByPidArray(@Param("pidArray") Long[] pidArray);

    /**
     * 查询子菜单列表
     * @param pidList 一级菜单id
     * @return 子菜单列表
     */
    List<SideMenu> selectByPidList(@Param("pidList") List<Long> pidList);

    /**
     * 查询一级菜单
     * @param map 查询参数
     * @return 一级菜单列表
     */
    List<SideMenu> selectLevel1(@Param("map") Map<String, Object> map);

    /**
     * 查询菜单列表（树状结构）
     * @return 菜单列表（树状结构）
     */
    List<SideMenu> selectTreeGrid();

    List<SideMenu> findAll();

    List<SideMenu> getList(@Param("map") Map<String, Object> map);

    /**
     * 根据请求地址查询权限组
     * @param url 请求地址
     * @return 权限组
     */
    String findAuthorityByUrl(@Param("url") String url);

    int deleteByEntityName(@Param("entityName") String entityName);

    List<ButtonAuthorityDto> findButtonAuthorityDtoByEntityName(@Param("entityName") String entityName);

    List<EasyUiTreeDto> findEasyUiTreeDtoByAuthority(@Param("authority") String authority);

    /**
     * 根据id返回子菜单详情
     * @param id 子菜单id
     * @return 子菜单详情
     */
    SideMenu getSubMenuDetailById(@Param("id") Long id);

    /**
     * 获取一级菜单
     * @return 一级菜单列表
     */
    List<SideMenuDto> getLevel1();
}