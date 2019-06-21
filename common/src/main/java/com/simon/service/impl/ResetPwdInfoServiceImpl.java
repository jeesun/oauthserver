
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.ResetPwdInfoMapper;
import com.simon.model.ResetPwdInfo;
import com.simon.repository.ResetPwdInfoRepository;
import com.simon.service.ResetPwdInfoService;
import lombok.var;
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
* @create 2018-08-06 20:56:26
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class ResetPwdInfoServiceImpl implements ResetPwdInfoService {
    @Autowired
    private ResetPwdInfoMapper resetPwdInfoMapper;

    @Autowired
    private ResetPwdInfoRepository resetPwdInfoRepository;

    @Override
    public long count() {
        return resetPwdInfoRepository.count();
    }

    @Override
    public ResetPwdInfo save(ResetPwdInfo resetPwdInfo){
        return resetPwdInfoRepository.save(resetPwdInfo);
    }

    @Override
    public List<ResetPwdInfo> save(List<ResetPwdInfo> resetPwdInfoList) {
        return resetPwdInfoRepository.save(resetPwdInfoList);
    }

    @Override
    public PageInfo<ResetPwdInfo> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<ResetPwdInfo> list = resetPwdInfoMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<ResetPwdInfo> findAll(Pageable pageable){
        return resetPwdInfoRepository.findAll(pageable);
    }

    @Override
    public List<ResetPwdInfo> findAll(){
        return resetPwdInfoRepository.findAll();
    }

    @Override
    public void delete(Long id){
        resetPwdInfoRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return resetPwdInfoMapper.deleteByIds(ids);
    }

    @Override
    public ResetPwdInfo findById(Long id){
        return resetPwdInfoRepository.findOne(id);
    }

    @Override
    public int insertList(List<ResetPwdInfo> list){
        return resetPwdInfoMapper.insertList(list);
    }

    @Override
    public int insert(ResetPwdInfo resetPwdInfo){
        return resetPwdInfoMapper.insert(resetPwdInfo);
    }

    @Override
    public int insertSelective(ResetPwdInfo resetPwdInfo){
        return resetPwdInfoMapper.insertSelective(resetPwdInfo);
    }

    @Override
    public int updateByPrimaryKey(ResetPwdInfo resetPwdInfo) {
        return resetPwdInfoMapper.updateByPrimaryKey(resetPwdInfo);
    }

    @Override
    public int updateByPrimaryKeySelective(ResetPwdInfo resetPwdInfo) {
        return resetPwdInfoMapper.updateByPrimaryKeySelective(resetPwdInfo);
    }

    @Override
    public PageInfo<ResetPwdInfo> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        var list = resetPwdInfoMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void batchSave(List<ResetPwdInfo> list) {

    }

    @Override
    public void batchUpdate(List<ResetPwdInfo> list) {

    }
}