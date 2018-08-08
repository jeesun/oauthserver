package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.OauthUser;
import org.apache.ibatis.annotations.Param;

public interface OauthUserMapper extends MyMapper<OauthUser> {
    int updatePwdByPhone(@Param("phone") String phone, @Param("password") String password);
}