package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.ResetPwdInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResetPwdInfoMapper extends MyMapper<ResetPwdInfo> {
    List<ResetPwdInfo> getList(@Param("map") Map<String, Object> map);
}