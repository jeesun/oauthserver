
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.SideMenuAuthorityMapper;
import com.simon.mapper.SideMenuMapper;
import com.simon.model.SideMenuAuthority;
import com.simon.repository.SideMenuAuthorityRepository;
import com.simon.service.SideMenuAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author SimonSun
 * @date 2019-01-14
 **/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class SideMenuAuthorityServiceImpl implements SideMenuAuthorityService {
    @Autowired
    private SideMenuAuthorityMapper sideMenuAuthorityMapper;

    @Autowired
    private SideMenuAuthorityRepository sideMenuAuthorityRepository;

    @Autowired
    private SideMenuMapper sideMenuMapper;

    @Override
    public long count() {
        return sideMenuAuthorityRepository.count();
    }

    @Override
    public SideMenuAuthority save(SideMenuAuthority sideMenuAuthority) {
        return sideMenuAuthorityRepository.save(sideMenuAuthority);
    }

    @Override
    public List<SideMenuAuthority> save(List<SideMenuAuthority> sideMenuAuthorityList) {
        return sideMenuAuthorityRepository.save(sideMenuAuthorityList);
    }

    @Override
    public PageInfo<SideMenuAuthority> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<SideMenuAuthority> list = sideMenuAuthorityMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<SideMenuAuthority> findAll(Pageable pageable) {
        return sideMenuAuthorityRepository.findAll(pageable);
    }

    @Override
    public List<SideMenuAuthority> findAll() {
        return sideMenuAuthorityRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        sideMenuAuthorityRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids) {
        return sideMenuAuthorityMapper.deleteByIds(ids);
    }

    @Override
    public SideMenuAuthority findById(Long id) {
        return sideMenuAuthorityRepository.findOne(id);
    }

    @Override
    public int insertList(List<SideMenuAuthority> list) {
        return sideMenuAuthorityMapper.insertList(list);
    }

    @Override
    public int insert(SideMenuAuthority sideMenuAuthority) {
        return sideMenuAuthorityMapper.insert(sideMenuAuthority);
    }

    @Override
    public int insertSelective(SideMenuAuthority sideMenuAuthority) {
        return sideMenuAuthorityMapper.insertSelective(sideMenuAuthority);
    }

    @Override
    public int updateByPrimaryKey(SideMenuAuthority sideMenuAuthority) {
        return sideMenuAuthorityMapper.updateByPrimaryKey(sideMenuAuthority);
    }

    @Override
    public int updateByPrimaryKeySelective(SideMenuAuthority sideMenuAuthority) {
        return sideMenuAuthorityMapper.updateByPrimaryKeySelective(sideMenuAuthority);
    }

    @Override
    public PageInfo<SideMenuAuthority> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<SideMenuAuthority> list = sideMenuAuthorityMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void updateAuth(List<Long> sideMenuIds, String authority) {
        sideMenuAuthorityMapper.deleteByAuthorityAndNotIn(authority, StringUtils.join(sideMenuIds, ","));
        //获取linkIds，并合并sideMenuIds和linkIds
        sideMenuIds.addAll(sideMenuMapper.getLinkIdsByIds(sideMenuIds.toArray(new Long[0])));
        //去重
        sideMenuIds = sideMenuIds.stream().distinct().collect(Collectors.toList());

        List<SideMenuAuthority> sideMenuAuthorityList = new ArrayList<>();
        for (Long sideMenuId : sideMenuIds) {
            SideMenuAuthority sideMenuAuthority = new SideMenuAuthority();
            sideMenuAuthority.setAuthority(authority);
            sideMenuAuthority.setSideMenuId(sideMenuId);
            sideMenuAuthorityList.add(sideMenuAuthority);
        }
        sideMenuAuthorityMapper.insertList(sideMenuAuthorityList);
    }
}