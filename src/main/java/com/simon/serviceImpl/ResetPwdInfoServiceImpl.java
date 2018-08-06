
package com.simon.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.ResetPwdInfoMapper;
import com.simon.model.ResetPwdInfo;
import com.simon.repository.ResetPwdInfoRepository;
import com.simon.service.ResetPwdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author SimonSun
* @create 2018-08-06 20:56:26
**/
@Service
@Transactional
public class ResetPwdInfoServiceImpl implements ResetPwdInfoService {
    @Autowired
    private ResetPwdInfoMapper resetPwdInfoMapper;

    @Autowired
    private ResetPwdInfoRepository resetPwdInfoRepository;

    @Override
    public ResetPwdInfo save(ResetPwdInfo resetPwdInfo){
        return resetPwdInfoRepository.save(resetPwdInfo);
    }

    @Override
    public List<ResetPwdInfo> save(List<ResetPwdInfo> resetPwdInfoList) {
        return resetPwdInfoRepository.save(resetPwdInfoList);
    }

    @Override
    public PageInfo<ResetPwdInfo> findAll(int pageNo){
        PageHelper.startPage(pageNo, AppConfig.DEFAULT_PAGE_SIZE);
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
}