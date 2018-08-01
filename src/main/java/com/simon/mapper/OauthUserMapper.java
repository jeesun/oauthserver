package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.OauthUser;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthUserMapper extends MyMapper<OauthUser> {
    int updatePwdByPhone(String phone, String password);
}