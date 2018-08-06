package com.simon.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.mapper.VeriCodeMapper;
import com.simon.model.VeriCode;
import com.simon.repository.VeriCodeRepository;
import com.simon.service.VeriCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 验证码
 *
 * @author simon
 * @create 2018-07-31 15:24
 **/

@Slf4j
@Service
public class VeriCodeServiceImpl implements VeriCodeService {
    @Autowired
    private VeriCodeMapper veriCodeMapper;

    @Autowired
    private VeriCodeRepository veriCodeRepository;

    @Override
    public VeriCode save(VeriCode veriCode){
        return veriCodeRepository.save(veriCode);
    }

    @Override
    public List<VeriCode> save(List<VeriCode> veriCodeList) {
        return veriCodeRepository.save(veriCodeList);
    }

    @Override
    public PageInfo<VeriCode> findAll(int pageNo){
        PageHelper.startPage(pageNo, AppConfig.DEFAULT_PAGE_SIZE);
        List<VeriCode> list = veriCodeMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<VeriCode> findAll(Pageable pageable){
        return veriCodeRepository.findAll(pageable);
    }

    @Override
    public List<VeriCode> findAll(){
        return veriCodeRepository.findAll();
    }

    @Override
    public void delete(Long id){
        veriCodeRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return veriCodeMapper.deleteByIds(ids);
    }

    @Override
    public VeriCode findById(Long id){
        return veriCodeRepository.findOne(id);
    }

    @Override
    public int insertList(List<VeriCode> list){
        return veriCodeMapper.insertList(list);
    }

    @Override
    public int insert(VeriCode veriCode){
        return veriCodeMapper.insert(veriCode);
    }

    @Override
    public int insertSelective(VeriCode veriCode){
        return veriCodeMapper.insertSelective(veriCode);
    }

    @Override
    public VeriCode findByPhone(String phone) {
        return veriCodeRepository.findByPhone(phone);
    }

    @Override
    public VeriCode findByPhoneAndCode(String phone, Integer code) {
        return veriCodeRepository.findByPhoneAndCode(phone, code);
    }
}
