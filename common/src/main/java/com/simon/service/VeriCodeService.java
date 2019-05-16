package com.simon.service;

import com.simon.common.service.BasicService;
import com.simon.model.VeriCode;

/**
 * 验证码
 *
 * @author simon
 * @create 2018-07-31 15:24
 **/

public interface VeriCodeService extends BasicService<VeriCode, Long> {
    VeriCode findByPhone(String phone);
    VeriCode findByPhoneAndCode(String phone, Integer code);
}
