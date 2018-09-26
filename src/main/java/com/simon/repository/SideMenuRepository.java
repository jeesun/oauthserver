package com.simon.repository;

import com.simon.model.SideMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2018-09-26
**/
@Repository
public interface SideMenuRepository extends JpaRepository<SideMenu, Long> {
}
