
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.AccountBindMapper;
import com.simon.model.AccountBind;
import com.simon.repository.AccountBindRepository;
import com.simon.service.AccountBindService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-12-04
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class AccountBindServiceImpl implements AccountBindService {
    @Autowired
    private AccountBindMapper accountBindMapper;

    @Autowired
    private AccountBindRepository accountBindRepository;

    @Override
    public long count() {
        return accountBindRepository.count();
    }

    @Override
    public AccountBind save(AccountBind accountBind){
        return accountBindRepository.save(accountBind);
    }

    @Override
    public List<AccountBind> save(List<AccountBind> accountBindList) {
        return accountBindRepository.save(accountBindList);
    }

    @Override
    public PageInfo<AccountBind> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<AccountBind> list = accountBindMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<AccountBind> findAll(Pageable pageable){
        return accountBindRepository.findAll(pageable);
    }

    @Override
    public List<AccountBind> findAll(){
        return accountBindRepository.findAll();
    }

    @Override
    public void delete(Long id){
        accountBindRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return accountBindMapper.deleteByIds(ids);
    }

    @Override
    public AccountBind findById(Long id){
        return accountBindRepository.findOne(id);
    }

    @Override
    public int insertList(List<AccountBind> list){
        return accountBindMapper.insertList(list);
    }

    @Override
    public int insert(AccountBind accountBind){
        return accountBindMapper.insert(accountBind);
    }

    @Override
    public int insertSelective(AccountBind accountBind){
        return accountBindMapper.insertSelective(accountBind);
    }

    @Override
    public int updateByPrimaryKey(AccountBind accountBind){
        return accountBindMapper.updateByPrimaryKey(accountBind);
    }

    @Override
    public int updateByPrimaryKeySelective(AccountBind accountBind){
        return accountBindMapper.updateByPrimaryKeySelective(accountBind);
    }

    @Override
    public PageInfo<AccountBind> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<AccountBind> list = accountBindMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<AccountBind> list) {

    }

    @Override
    public void batchUpdate(List<AccountBind> list) {

    }
}