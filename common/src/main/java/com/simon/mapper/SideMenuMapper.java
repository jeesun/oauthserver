package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.ButtonAuthorityDto;
import com.simon.dto.EasyUiTreeDto;
import com.simon.dto.SideMenuDto;
import com.simon.model.SideMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SideMenuMapper extends MyMapper<SideMenu> {
    List<SideMenu> selectByPid(@Param("pid") Long pid, @Param("language") String language);

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
    List<SideMenu> selectTreeGrid(String language);

    /**
     * 根据请求地址查询权限组
     * @param url 请求地址
     * @return 权限组
     */
    String findAuthorityByUrl(@Param("url") String url);

    int deleteByEntityName(@Param("entityName") String entityName);

    List<ButtonAuthorityDto> findButtonAuthorityDtoByEntityName(@Param("entityName") String entityName);

    List<EasyUiTreeDto> findEasyUiTreeDtoByAuthority(@Param("authority") String authority, @Param("language") String language);

    /**
     * 获取一级菜单
     * @return 一级菜单列表
     */
    List<SideMenuDto> getLevel1(@Param("language") String language);

    /**
     * 根据菜单id获取关联
     * @param ids 菜单ids
     * @return 关联ids
     */
    List<Long> getLinkIdsByIds(@Param("ids") Long[] ids);

    SideMenu findById(@Param("id") Long id, @Param("language") String language);

    @Select("select id from t_side_menu where pid=#{pid}")
    List<Long> selectIdByPid(@Param("pid") Long pid);
}