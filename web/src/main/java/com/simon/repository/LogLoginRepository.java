package com.simon.repository;

import com.simon.model.LogLogin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 登录日志
 *
 * @author simon
 * @create 2018-07-27 1:31
 **/

public interface LogLoginRepository extends JpaRepository<LogLogin, Long> {
}
