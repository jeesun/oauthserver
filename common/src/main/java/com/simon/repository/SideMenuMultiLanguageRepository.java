package com.simon.repository;

import com.simon.model.SideMenuMultiLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author jeesun
* @date 2019-05-30
**/
@Repository
public interface SideMenuMultiLanguageRepository extends JpaRepository<SideMenuMultiLanguage, Long> {
    /**
     * 根据侧边栏菜单id列表删除多语言信息
     * @param sideMenuIds 侧边栏菜单id列表
     */
    void deleteBySideMenuIdIn(List<Long> sideMenuIds);
}
