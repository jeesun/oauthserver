package com.simon.repository;

import com.simon.model.QuartzJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2018-12-21
**/
@Repository
public interface QuartzJobRepository extends JpaRepository<QuartzJob, Long> {
}
