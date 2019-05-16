package com.simon.repository;

import com.simon.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2019-04-25
**/
@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
}
