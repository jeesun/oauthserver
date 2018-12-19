
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.SideMenuMapper;
import com.simon.model.SideMenu;
import com.simon.repository.SideMenuRepository;
import com.simon.service.SideMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-09-26
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class SideMenuServiceImpl implements SideMenuService {
    @Autowired
    private SideMenuMapper sideMenuMapper;

    @Autowired
    private SideMenuRepository sideMenuRepository;

    @Override
    public long count() {
        return sideMenuRepository.count();
    }

    @Override
    public SideMenu save(SideMenu sideMenu){
        return sideMenuRepository.save(sideMenu);
    }

    @Override
    public List<SideMenu> save(List<SideMenu> sideMenuList) {
        return sideMenuRepository.save(sideMenuList);
    }

    @Override
    public PageInfo<SideMenu> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (org.apache.commons.lang3.StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<SideMenu> list = sideMenuMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<SideMenu> findAll(Pageable pageable){
        return sideMenuRepository.findAll(pageable);
    }

    @Override
    public List<SideMenu> findAll(){
        return sideMenuMapper.findAll();
    }

    @Override
    public void delete(Long id){
        sideMenuRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return sideMenuMapper.deleteByIds(ids);
    }

    @Override
    public SideMenu findById(Long id){
        return sideMenuRepository.findOne(id);
    }

    @Override
    public int insertList(List<SideMenu> list){
        return sideMenuMapper.insertList(list);
    }

    @Override
    public int insert(SideMenu sideMenu){
        return sideMenuMapper.insert(sideMenu);
    }

    @Override
    public int insertSelective(SideMenu sideMenu){
        return sideMenuMapper.insertSelective(sideMenu);
    }

    @Override
    public int updateByPrimaryKey(SideMenu sideMenu){
        return sideMenuMapper.updateByPrimaryKey(sideMenu);
    }

    @Override
    public int updateByPrimaryKeySelective(SideMenu sideMenu){
        return sideMenuMapper.updateByPrimaryKeySelective(sideMenu);
    }

    @Override
    public PageInfo<SideMenu> getAll(Map<String, Object> params, Integer limit, Integer offset) {
        PageHelper.startPage(offset/limit + 1, limit);
        List<SideMenu> list = sideMenuMapper.selectLevel1(params);
        PageInfo<SideMenu> pageInfo = new PageInfo<>(list);
        List<Long> pids = new ArrayList<>();
        List<SideMenu> resultList = pageInfo.getList();
        for(SideMenu sideMenu : resultList){
            pids.add(sideMenu.getId());
        }
        resultList.addAll(sideMenuMapper.selectByPidList(pids));
        pageInfo.setList(resultList);
        return pageInfo;
    }

    @Override
    public List<SideMenu> getAll() {
        return sideMenuMapper.selectTreeGrid();
    }

    @Override
    public PageInfo<SideMenu> getList(Map<String, Object> params, Integer limit, Integer offset, String orderBy) {
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(offset/limit + 1, limit);
        }else{
            PageHelper.startPage(offset/limit + 1, limit, orderBy);
        }

        List<SideMenu> list = sideMenuMapper.selectLevel1(params);
        return new PageInfo<>(list);
    }
}