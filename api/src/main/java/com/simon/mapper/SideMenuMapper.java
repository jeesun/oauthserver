package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.SideMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
}