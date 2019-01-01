package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.AccountBind;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AccountBindMapper extends MyMapper<AccountBind> {
    List<AccountBind> getList(@Param("map") Map<String, Object> map);
}