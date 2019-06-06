package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.dto.AuthorityDto;
import com.simon.model.Authority;

import java.util.List;

/**
* @author SimonSun
* @date 2018-11-14
**/
public interface AuthorityService extends BasicService<Authority, Long> {
    PageInfo<AuthorityDto> getDtoList(Long userId, String username, String authority, String language, Integer pageNo, Integer pageSize, String orderBy);
    List<Authority> findByUserId(Long userId);

    void updateByDto(AuthorityDto authorityDto);

    int deleteByUserIds(String userIds);

    /**
     * 根据用户id查询权限dto
     * @param userId 用户id
     * @param language 语言
     * @return 权限dto
     */
    AuthorityDto findDtoByUserId(Long userId, String language);
}