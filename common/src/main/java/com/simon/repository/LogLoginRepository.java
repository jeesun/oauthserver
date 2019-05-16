package com.simon.repository;

import com.simon.model.LogLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 登录日志
 *
 * @author simon
 * @create 2018-07-27 1:31
 **/
@Repository
public interface LogLoginRepository extends JpaRepository<LogLogin, Long> {
}
