package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.SideMenuMultiLanguage;
import com.simon.provider.SideMenuMultiLanguageProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
* @author jeesun
* @date 2019-05-30
**/
@Mapper
public interface SideMenuMultiLanguageMapper extends MyMapper<SideMenuMultiLanguage> {
    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("BaseResultMap")
    @SelectProvider(type = SideMenuMultiLanguageProvider.class, method = "getList")
    List<SideMenuMultiLanguage> getList(Map<String, Object> map);


    /**
     * 根据侧边栏菜单id和语言更新菜单名称
     * @param name 菜单名称
     * @param sideMenuId 侧边栏菜单id
     * @param language 语言
     * @return 影响行数
     */
    int updateNameBySideMenuIdAndLanguage(@Param("name") String name, @Param("sideMenuId") Long sideMenuId, @Param("language") String language);

    /**
     * 根据侧边栏菜单id和语言查询，判断记录是否存在
     * @param sideMenuId 侧边栏菜单id
     * @param language 语言
     * @return count
     */
    int countBySideMenuIdAndLanguage(@Param("sideMenuId") Long sideMenuId, @Param("language") String language);
}