
package com.simon.serviceImpl;

import com.simon.mapper.SideMenuMapper;
import com.simon.model.SideMenu;
import com.simon.service.SideMenuService;
import com.simon.repository.SideMenuRepository;
import com.simon.common.config.AppConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

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
    public PageInfo<SideMenu> findAll(int pageNo){
        PageHelper.startPage(pageNo, AppConfig.DEFAULT_PAGE_SIZE);
        List<SideMenu> list = sideMenuMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<SideMenu> findAll(Pageable pageable){
        return sideMenuRepository.findAll(pageable);
    }

    @Override
    public List<SideMenu> findAll(){
        return sideMenuRepository.findAll();
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
}