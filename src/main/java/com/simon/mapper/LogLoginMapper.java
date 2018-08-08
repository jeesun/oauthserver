package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.LogLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface LogLoginMapper extends MyMapper<LogLogin> {
    LogLogin selectByPrimaryKey(@Param("id") Long id);
}