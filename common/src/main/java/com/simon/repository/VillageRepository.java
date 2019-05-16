package com.simon.repository;

import com.simon.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2019-04-24
**/
@Repository
public interface VillageRepository extends JpaRepository<Village, Integer> {
}
