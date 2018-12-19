package com.simon.repository;

import com.simon.model.VeriCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 验证码
 *
 * @author simon
 * @create 2018-07-25 22:40
 **/

public interface VeriCodeRepository extends JpaRepository<VeriCode, Long> {
    VeriCode findByPhone(String phone);
    VeriCode findByPhoneAndCode(String phone, Integer code);
}
