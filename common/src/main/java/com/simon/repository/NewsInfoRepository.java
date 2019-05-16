package com.simon.repository;

import com.simon.model.NewsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2019-01-20
**/
@Repository
public interface NewsInfoRepository extends JpaRepository<NewsInfo, Long> {
}
