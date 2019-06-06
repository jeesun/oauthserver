package com.simon.repository;

import com.simon.model.SideMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author SimonSun
* @date 2018-09-26
**/
@Repository
public interface SideMenuRepository extends JpaRepository<SideMenu, Long> {
    /**
     * 根据父菜单id删除记录
     * @param pid 父菜单id
     */
    void deleteByPid(Long pid);

    /**
     * 根据父菜单id集合删除记录
     * @param pids 父菜单id集合
     */
    void deleteByPidIn(List<Long> pids);

    /**
     * 根据菜单id或父菜单id删除记录
     * @param id 菜单id
     * @param pid 父菜单id
     */
    void deleteByIdOrPid(Long id, Long pid);
}
