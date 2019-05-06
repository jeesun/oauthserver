package com.simon.repository;

import com.simon.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2019-04-24
**/
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
