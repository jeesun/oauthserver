package com.simon.repository;

import com.simon.model.SideMenuAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2019-01-14
**/
@Repository
public interface SideMenuAuthorityRepository extends JpaRepository<SideMenuAuthority, Long> {
    /**
     * 根据侧边栏菜单id删除记录
     * @param sideMenuId 侧边栏菜单id
     */
    void deleteBySideMenuId(Long sideMenuId);
}
