package com.simon.repository;

import com.simon.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2019-04-24
**/
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
