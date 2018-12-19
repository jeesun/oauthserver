package com.simon.repository;

import com.simon.model.NewsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 新闻
 *
 * @author simon
 * @create 2018-08-03 22:32
 **/

public interface NewsInfoRepository extends JpaRepository<NewsInfo, Long> {
}
