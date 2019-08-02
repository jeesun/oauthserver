
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.dto.AuthorityDto;
import com.simon.mapper.AuthorityMapper;
import com.simon.model.Authority;
import com.simon.repository.AuthorityRepository;
import com.simon.service.AuthorityService;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SimonSun
 * @date 2018-11-14
 **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class AuthorityServiceImpl extends CrudServiceImpl<Authority, Long> implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public PageInfo<AuthorityDto> getDtoList(Long userId, String username, String authority, String language, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        var list = authorityMapper.getDtoList(userId, username, authority, language);
        return new PageInfo<>(list);
    }

    @Override
    public List<Authority> findByUserId(Long userId) {
        return authorityRepository.findByUserId(userId);
    }

    @Override
    public void updateByDto(AuthorityDto authorityDto) {
        authorityMapper.deleteByUserIds(String.valueOf(authorityDto.getUserId()));
        String[] authorities = authorityDto.getAuthorities();
        if (null != authorities && authorities.length > 0) {
            List<Authority> authorityList = new ArrayList<>();
            for(int i = 0; i < authorities.length; i++){
                Authority authority = new Authority();
                authority.setUserId(authorityDto.getUserId());
                authority.setAuthority(authorities[i]);
                authorityList.add(authority);
            }
            authorityMapper.insertList(authorityList);
        }
    }

    @Override
    public int deleteByUserIds(String userIds) {
        return authorityMapper.deleteByUserIds(userIds);
    }

    @Override
    public AuthorityDto findDtoByUserId(Long userId, String language) {
        List<AuthorityDto> dtoList = authorityMapper.getDtoList(userId, null, null, language);
        if (null == dtoList || dtoList.size() <= 0) {
            return null;
        } else {
            return dtoList.get(0);
        }
    }
}