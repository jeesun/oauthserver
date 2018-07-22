package com.simon.mapper;

import com.simon.model.Authority;

public interface AuthorityMapper {
    int insert(Authority record);

    int insertSelective(Authority record);
}