package com.simon.repository;

import com.simon.model.FontAwesome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2019-04-29
**/
@Repository
public interface FontAwesomeRepository extends JpaRepository<FontAwesome, Integer> {
}
