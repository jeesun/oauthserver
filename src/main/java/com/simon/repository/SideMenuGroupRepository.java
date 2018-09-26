package com.simon.repository;

import com.simon.model.SideMenuGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2018-09-26
**/
@Repository
public interface SideMenuGroupRepository extends JpaRepository<SideMenuGroup, Long> {
}
