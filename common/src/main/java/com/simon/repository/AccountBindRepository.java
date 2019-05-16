package com.simon.repository;

import com.simon.model.AccountBind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2018-12-04
**/
@Repository
public interface AccountBindRepository extends JpaRepository<AccountBind, Long> {
}
