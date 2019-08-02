
package com.simon.service.impl;

import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.SideMenuAuthorityMapper;
import com.simon.mapper.SideMenuMapper;
import com.simon.model.SideMenuAuthority;
import com.simon.repository.SideMenuAuthorityRepository;
import com.simon.service.SideMenuAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SimonSun
 * @date 2019-01-14
 **/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class SideMenuAuthorityServiceImpl extends CrudServiceImpl<SideMenuAuthority, Long> implements SideMenuAuthorityService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SideMenuAuthorityMapper sideMenuAuthorityMapper;

    @Autowired
    private SideMenuAuthorityRepository sideMenuAuthorityRepository;

    @Autowired
    private SideMenuMapper sideMenuMapper;

    @Override
    public void updateAuth(List<Long> sideMenuIds, String authority) {
        sideMenuAuthorityMapper.deleteByAuthorityAndNotIn(authority, StringUtils.join(sideMenuIds, ","));
        //获取linkIds，并合并sideMenuIds和linkIds
        sideMenuIds.addAll(sideMenuMapper.getLinkIdsByIds(sideMenuIds.toArray(new Long[0])));
        //去重
        sideMenuIds = sideMenuIds.stream().distinct().collect(Collectors.toList());
        //去空
        sideMenuIds.removeAll(Collections.singleton(null));

        List<SideMenuAuthority> sideMenuAuthorityList = new ArrayList<>();
        for (Long sideMenuId : sideMenuIds) {
            SideMenuAuthority sideMenuAuthority = new SideMenuAuthority();
            sideMenuAuthority.setAuthority(authority);
            sideMenuAuthority.setSideMenuId(sideMenuId);
            sideMenuAuthorityList.add(sideMenuAuthority);
        }
        sideMenuAuthorityMapper.insertList(sideMenuAuthorityList);
    }

    @Override
    public void batchSave(List<SideMenuAuthority> list) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        SideMenuAuthorityMapper mapper = sqlSession.getMapper(SideMenuAuthorityMapper.class);
        for (SideMenuAuthority item : list) {
            mapper.insert(item);
        }
        sqlSession.commit();
    }

    @Override
    public void batchUpdate(List<SideMenuAuthority> list) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        SideMenuAuthorityMapper mapper = sqlSession.getMapper(SideMenuAuthorityMapper.class);
        for (SideMenuAuthority item : list) {
            mapper.updateByPrimaryKeySelective(item);
        }
        sqlSession.commit();
    }
}