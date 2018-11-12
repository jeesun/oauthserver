package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author simon
 * @create 2018-07-31 19:56
 **/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
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
    public long count() {
        return oauthUserRepository.count();
    }

    @Override
    public OauthUser save(OauthUser oauthUser) {
        return oauthUserRepository.save(oauthUser);
    }

    @Override
    public List<OauthUser> save(List<OauthUser> modelList) {
        return oauthUserRepository.save(modelList);
    }

    @Override
    public PageInfo<OauthUser> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<OauthUser> list = oauthUserMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<OauthUser> findAll(Pageable pageable) {
        return oauthUserRepository.findAll(pageable);
    }

    @Override
    public List<OauthUser> findAll() {
        return oauthUserRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        oauthUserRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids) {
        return oauthUserMapper.deleteByIds(ids);
    }

    @Override
    public OauthUser findById(Long id) {
        return oauthUserRepository.findById(id);
    }

    @Override
    public int insertList(List<OauthUser> list) {
        return oauthUserMapper.insertList(list);
    }

    @Override
    public int insert(OauthUser model) {
        return oauthUserMapper.insert(model);
    }

    @Override
    public int insertSelective(OauthUser model) {
        return oauthUserMapper.insertSelective(model);
    }

    @Override
    public int updateByPrimaryKey(OauthUser model) {
        return oauthUserMapper.updateByPrimaryKey(model);
    }

    @Override
    public int updateByPrimaryKeySelective(OauthUser model) {
        return oauthUserMapper.updateByPrimaryKeySelective(model);
    }

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

    @Override
    public PageInfo<OauthUser> getList(Map<String, Object> params, Integer limit, Integer offset, String orderBy) {
        orderBy = orderBy.trim();
        if (org.springframework.util.StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(offset/limit + 1, limit);
        }else{
            PageHelper.startPage(offset/limit + 1, limit, orderBy);
        }

        List<OauthUser> list = oauthUserMapper.findByMap(params);
        return new PageInfo<>(list);
    }
}