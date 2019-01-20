package com.simon.repository;

import com.simon.model.SideMenuAuthority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2019-01-14
**/
@Repository
public interface SideMenuAuthorityRepository extends JpaRepository<SideMenuAuthority, Long> {
}
