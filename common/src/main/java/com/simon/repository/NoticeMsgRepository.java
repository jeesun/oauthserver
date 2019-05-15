package com.simon.repository;

import com.simon.model.NoticeMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2018-11-24
**/
@Repository
public interface NoticeMsgRepository extends JpaRepository<NoticeMsg, Long> {
}
