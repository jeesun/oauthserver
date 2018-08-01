package com.simon.serviceImpl;

import com.simon.model.VeriCode;
import com.simon.repository.VeriCodeRepository;
import com.simon.service.VeriCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private VeriCodeRepository veriCodeRepository;

    @Override
    public VeriCode findByPhone(String phone) {
        return veriCodeRepository.findByPhone(phone);
    }

    @Override
    public VeriCode findByPhoneAndCode(String phone, Integer code) {
        return veriCodeRepository.findByPhoneAndCode(phone, code);
    }
}
