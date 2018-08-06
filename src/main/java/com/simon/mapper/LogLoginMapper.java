package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.LogLogin;
import org.springframework.stereotype.Repository;

@Repository
public interface LogLoginMapper extends MyMapper<LogLogin> {
    LogLogin selectByPrimaryKey(Long id);
}