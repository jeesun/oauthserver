package com.simon.mapper;

import com.simon.model.OauthUser;

public interface OauthUserMapper {
    int deleteByPrimaryKey(String username);

    int insert(OauthUser record);

    int insertSelective(OauthUser record);

    OauthUser selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(OauthUser record);

    int updateByPrimaryKey(OauthUser record);
}