package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.dto.AuthorityDto;
import com.simon.model.Authority;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-11-14
**/
public interface AuthorityService extends BasicService<Authority, Long> {
    PageInfo<AuthorityDto> getDtoList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy);
    List<Authority> findByUserId(Long userId);

    void updateByDto(AuthorityDto authorityDto);

    int deleteByUserIds(String userIds);
}