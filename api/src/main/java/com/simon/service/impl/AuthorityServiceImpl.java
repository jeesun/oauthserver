
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.dto.AuthorityDto;
import com.simon.mapper.AuthorityMapper;
import com.simon.model.Authority;
import com.simon.repository.AuthorityRepository;
import com.simon.service.AuthorityService;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-11-14
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public long count() {
        return authorityRepository.count();
    }

    @Override
    public Authority save(Authority authority){
        return authorityRepository.save(authority);
    }

    @Override
    public List<Authority> save(List<Authority> authorityList) {
        return authorityRepository.save(authorityList);
    }

    @Override
    public PageInfo<Authority> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Authority> list = authorityMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<Authority> findAll(Pageable pageable){
        return authorityRepository.findAll(pageable);
    }

    @Override
    public List<Authority> findAll(){
        return authorityRepository.findAll();
    }

    @Override
    public void delete(Long id){
        authorityRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return authorityMapper.deleteByIds(ids);
    }

    @Override
    public Authority findById(Long id){
        return authorityRepository.findOne(id);
    }

    @Override
    public int insertList(List<Authority> list){
        return authorityMapper.insertList(list);
    }

    @Override
    public int insert(Authority authority){
        return authorityMapper.insert(authority);
    }

    @Override
    public int insertSelective(Authority authority){
        return authorityMapper.insertSelective(authority);
    }

    @Override
    public int updateByPrimaryKey(Authority authority){
        return authorityMapper.updateByPrimaryKey(authority);
    }

    @Override
    public int updateByPrimaryKeySelective(Authority authority){
        return authorityMapper.updateByPrimaryKeySelective(authority);
    }

    @Override
    public PageInfo<Authority> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        return null;
    }

    @Override
    public PageInfo<AuthorityDto> getDtoList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        var list = authorityMapper.getDtoList(params);
        return new PageInfo<>(list);
    }

    @Override
    public List<Authority> findByUserId(Long userId) {
        return authorityRepository.findByUserId(userId);
    }

    @Override
    public void updateByDto(AuthorityDto authorityDto) {
        String auth = authorityDto.getAuthority();
        List<Authority> oldRecords = authorityRepository.findByUserId(authorityDto.getUserId());
        List<Long> toDeleteIds = new ArrayList<>();
        List<Authority> authorityList = new ArrayList<>();
        if(StringUtils.isNotEmpty(auth)){
            String[] authArr = auth.split(",");
            if(authArr.length > 0){
                for(int i = 0; i < authArr.length; i++){
                    boolean toAdd = true;
                    for(int j = 0; j < oldRecords.size(); j++){
                        if (authArr[i].equals(oldRecords.get(j).getAuthority())){
                            toAdd = false;
                            break;
                        }
                    }
                    if (toAdd){
                        Authority authority = new Authority();
                        authority.setCreateDate(new Date());
                        authority.setUserId(authorityDto.getUserId());
                        authority.setAuthority(authArr[i]);
                        authorityList.add(authority);
                    }
                }

            }
        }
        if(StringUtils.isNotEmpty(auth)){
            oldRecords.forEach(oldRecord -> {
                if(!auth.contains(oldRecord.getAuthority())){
                    toDeleteIds.add(oldRecord.getId());
                }
            });
        }else{
            oldRecords.forEach(oldRecord -> {
                toDeleteIds.add(oldRecord.getId());
            });
        }
        if(toDeleteIds.size() > 0){
            authorityMapper.deleteByIds(StringUtils.join(toDeleteIds, ","));
        }
        if(authorityList.size() > 0){
            authorityRepository.save(authorityList);
        }
    }

    @Override
    public int deleteByUserIds(String userIds) {
        return authorityMapper.deleteByUserIds(userIds);
    }
}