package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.StatisticDto;
import com.simon.model.OauthUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OauthUserMapper extends MyMapper<OauthUser> {
    int updatePwdByPhone(@Param("phone") String phone, @Param("password") String password);

    List<OauthUser> findByMap(@Param("map") Map<String, Object> map);

    List<StatisticDto> sexRatio();
}