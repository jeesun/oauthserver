
package com.simon.serviceImpl;

import com.simon.mapper.SideMenuGroupMapper;
import com.simon.model.SideMenuGroup;
import com.simon.service.SideMenuGroupService;
import com.simon.repository.SideMenuGroupRepository;
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
public class SideMenuGroupServiceImpl implements SideMenuGroupService {
    @Autowired
    private SideMenuGroupMapper sideMenuGroupMapper;

    @Autowired
    private SideMenuGroupRepository sideMenuGroupRepository;

    @Override
    public long count() {
        return sideMenuGroupRepository.count();
    }

    @Override
    public SideMenuGroup save(SideMenuGroup sideMenuGroup){
        return sideMenuGroupRepository.save(sideMenuGroup);
    }

    @Override
    public List<SideMenuGroup> save(List<SideMenuGroup> sideMenuGroupList) {
        return sideMenuGroupRepository.save(sideMenuGroupList);
    }

    @Override
    public PageInfo<SideMenuGroup> findAll(int pageNo){
        PageHelper.startPage(pageNo, AppConfig.DEFAULT_PAGE_SIZE);
        List<SideMenuGroup> list = sideMenuGroupMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<SideMenuGroup> findAll(Pageable pageable){
        return sideMenuGroupRepository.findAll(pageable);
    }

    @Override
    public List<SideMenuGroup> findAll(){
        return sideMenuGroupRepository.findAll();
    }

    @Override
    public void delete(Long id){
        sideMenuGroupRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return sideMenuGroupMapper.deleteByIds(ids);
    }

    @Override
    public SideMenuGroup findById(Long id){
        return sideMenuGroupRepository.findOne(id);
    }

    @Override
    public int insertList(List<SideMenuGroup> list){
        return sideMenuGroupMapper.insertList(list);
    }

    @Override
    public int insert(SideMenuGroup sideMenuGroup){
        return sideMenuGroupMapper.insert(sideMenuGroup);
    }

    @Override
    public int insertSelective(SideMenuGroup sideMenuGroup){
        return sideMenuGroupMapper.insertSelective(sideMenuGroup);
    }

    @Override
    public int updateByPrimaryKey(SideMenuGroup sideMenuGroup){
        return sideMenuGroupMapper.updateByPrimaryKey(sideMenuGroup);
    }

    @Override
    public int updateByPrimaryKeySelective(SideMenuGroup sideMenuGroup){
        return sideMenuGroupMapper.updateByPrimaryKeySelective(sideMenuGroup);
    }
}