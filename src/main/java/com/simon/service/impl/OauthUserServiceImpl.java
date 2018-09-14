package com.simon.service.impl;

import com.simon.common.exception.CodeInvalidException;
import com.simon.common.exception.PhoneRegisteredException;
import com.simon.common.exception.UserNotValidException;
import com.simon.mapper.OauthUserMapper;
import com.simon.model.Authority;
import com.simon.model.OauthUser;
import com.simon.repository.AuthorityRepository;
import com.simon.repository.OauthUserRepository;
import com.simon.repository.VeriCodeRepository;
import com.simon.service.OauthUserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户
 *
 * @author simon
 * @create 2018-07-31 19:56
 **/
@Slf4j
@Service
public class OauthUserServiceImpl implements OauthUserService {
    @Autowired
    private OauthUserMapper oauthUserMapper;

    @Autowired
    private OauthUserRepository oauthUserRepository;

    @Autowired
    private VeriCodeRepository veriCodeRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void register(Integer code, String phone, String password) {
        //加密密码
        var encoder = new BCryptPasswordEncoder(11);
        password = encoder.encode(password);
        if(null != code){
            var veriCode = veriCodeRepository.findByPhoneAndCode(phone, code);
            if(null != veriCode){
                register(phone, password);
            }else{
                throw new CodeInvalidException();
            }
            register(phone, password);
        }
    }

    @Transactional
    @Override
    public void register(String phone, String password){
        if(null != oauthUserRepository.findByPhone(phone)){
            throw new PhoneRegisteredException();
        }

        var oauthUser = new OauthUser();
        oauthUser.setUsername("user" + phone.substring(phone.length()-4, phone.length()));
        oauthUser.setPhone(phone);
        oauthUser.setPassword(password);
        oauthUser.setEnabled(true);
        oauthUser = oauthUserRepository.save(oauthUser);

        var authority = new Authority();
        authority.setUserId(oauthUser.getId());
        authority.setAuthority("ROLE_USER");
        authority = authorityRepository.save(authority);
    }

    @Override
    public int updatePwdByCode(String phone, Integer code, String newPwd) {
        //加密密码
        var encoder = new BCryptPasswordEncoder(11);
        newPwd = encoder.encode(newPwd);
        var veriCode = veriCodeRepository.findByPhoneAndCode(phone, code);
        if(null != veriCode){
            return oauthUserMapper.updatePwdByPhone(phone, newPwd);
        }else{
            throw new CodeInvalidException();
        }
    }

    @Override
    public int updatePwdByOldPwd(String phone, String oldPwd, String newPwd) {
        var oauthUser = oauthUserRepository.findByPhone(phone);
        if(null == oauthUser){
            throw new UserNotValidException();
        }

        var encoder = new BCryptPasswordEncoder(11);
        if(encoder.matches(oldPwd, oauthUser.getPassword())){
            oauthUser.setPassword(encoder.encode(newPwd));
            oauthUserRepository.save(oauthUser);
            return 1;
        }else{
            throw new UserNotValidException();
        }
    }

    @Cacheable(key="#username", cacheNames = "oauthUserCache")
    @Override
    public OauthUser findByUsername(String username) {
        return oauthUserRepository.findByUsername(username);
    }

    @Override
    public OauthUser findByPhone(String phone) {
        return oauthUserRepository.findByPhone(phone);
    }

    @Override
    public OauthUser save(OauthUser oauthUser) {
        return oauthUserRepository.save(oauthUser);
    }

    @Transactional
    @Override
    public int update(String username, String newUsername, String newPhone, String newEmail) {
        var oauthUser = oauthUserRepository.findByUsername(username);
        if(null != newUsername){
            oauthUser.setUsername(newUsername);
        }
        if(null != newPhone){
            oauthUser.setPhone(newPhone);
        }
        if(null != newEmail){
            oauthUser.setEmail(newEmail);
        }
        oauthUser = oauthUserRepository.save(oauthUser);
        if(null != oauthUser){
            return 1;
        }
        return 0;
    }
}