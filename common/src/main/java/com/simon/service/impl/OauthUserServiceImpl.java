package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.domain.UserEntity;
import com.simon.common.exception.CodeInvalidException;
import com.simon.common.exception.PhoneRegisteredException;
import com.simon.common.exception.UserExistsException;
import com.simon.common.exception.UserNotValidException;
import com.simon.common.utils.BeanUtils;
import com.simon.common.utils.UsernameUtil;
import com.simon.common.utils.ValidUtil;
import com.simon.dto.AuthorityDto;
import com.simon.dto.StatisticDto;
import com.simon.mapper.AuthorityMapper;
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
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public long count() {
        return oauthUserRepository.count();
    }

    @Override
    public OauthUser save(OauthUser oauthUser) {
        oauthUser.setPassword(passwordEncoder.encode(oauthUser.getPassword()));
        oauthUserMapper.insertSelective(oauthUser);

        if(StringUtils.isEmpty(oauthUser.getAuthorities())){
            Authority authority = new Authority();
            authority.setUserId(oauthUser.getId());
            authority.setAuthority(AppConfig.ROLE_USER);
            authorityMapper.insertSelective(authority);
        }else{
            String[] authorities = oauthUser.getAuthorities().split(",");
            List<Authority> authorityList = new ArrayList<>();
            for(int i = 0; i < authorities.length; i++){
                Authority authority = new Authority();
                authority.setUserId(oauthUser.getId());
                authority.setAuthority(authorities[i]);
                authorityList.add(authority);
            }
            authorityMapper.insertList(authorityList);
        }

        return oauthUser;
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

    //@CacheEvict(key = "#id", cacheNames = "oauthUserCache")
    @Override
    public void delete(Long id) {
        OauthUser oauthUser = oauthUserRepository.getOne(id);
        oauthUserRepository.delete(id);
        if(null != cacheManager){
            Cache cache = cacheManager.getCache("oauthUserCache");
            if(null != cache){
                cache.evict(oauthUser.getUsername());
                cache.evict(oauthUser.getEmail());
                cache.evict(oauthUser.getPhone());
            }
        }
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
    public int insert(OauthUser oauthUser) {
        return insertSelective(oauthUser);
    }

    @Override
    public int insertSelective(OauthUser oauthUser) {
        oauthUser.setPassword(passwordEncoder.encode(oauthUser.getPassword()));
        int result = oauthUserMapper.insertSelective(oauthUser);

        if(StringUtils.isEmpty(oauthUser.getAuthorities())){
            Authority authority = new Authority();
            authority.setUserId(oauthUser.getId());
            authority.setAuthority(AppConfig.ROLE_USER);
            authorityMapper.insertSelective(authority);
        }else{
            String[] authorities = oauthUser.getAuthorities().split(",");
            List<Authority> authorityList = new ArrayList<>();
            for(int i = 0; i < authorities.length; i++){
                Authority authority = new Authority();
                authority.setUserId(oauthUser.getId());
                authority.setAuthority(authorities[i]);
                authorityList.add(authority);
            }
            authorityMapper.insertList(authorityList);
        }
        return result;
    }

    //@CachePut(key="#model.username", cacheNames = {"oauthUserCache"})
    @Override
    public int updateByPrimaryKey(OauthUser model) {
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        int affectLineNum = oauthUserMapper.updateByPrimaryKey(model);
        if(null != cacheManager){
            Cache cache = cacheManager.getCache("oauthUserCache");
            if(null != cache){
                OauthUser target = oauthUserRepository.findById(model.getId());
                BeanUtils.copyPropertiesIgnoreNull(model, target);

                UserEntity userEntity = new UserEntity(target.getId(), target.getUsername(), target.getPassword(), target.getEnabled(), target.getPhone(), target.getEmail(), target.getAddress(), target.getBirth(), target.getAge(), target.getHeadPhoto(), target.getPersonBrief(), target.getSex(), authorityRepository.findByUserId(target.getId()));

                //刷新缓存
                cache.put(model.getUsername(), userEntity);
                cache.put(model.getEmail(), userEntity);
                cache.put(model.getPhone(), userEntity);
            }
        }

        return affectLineNum;
    }

    //@CachePut(key="#model.username", cacheNames = {"oauthUserCache"})
    @Override
    public int updateByPrimaryKeySelective(OauthUser model) {
        int affectLineNum = oauthUserMapper.updateByPrimaryKeySelective(model);
        if(null != cacheManager){
            Cache cache = cacheManager.getCache("oauthUserCache");
            if(null != cache){
                OauthUser target = oauthUserRepository.findById(model.getId());
                BeanUtils.copyPropertiesIgnoreNull(model, target);

                UserEntity userEntity = new UserEntity(target.getId(), target.getUsername(), target.getPassword(), target.getEnabled(), target.getPhone(), target.getEmail(), target.getAddress(), target.getBirth(), target.getAge(), target.getHeadPhoto(), target.getPersonBrief(), target.getSex(), authorityRepository.findByUserId(target.getId()));

                //刷新缓存
                cache.put(model.getUsername(), userEntity);
                cache.put(model.getEmail(), userEntity);
                cache.put(model.getPhone(), userEntity);
            }
        }
        return affectLineNum;
    }

    @Override
    public PageInfo<OauthUser> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<OauthUser> list = oauthUserMapper.getList(params);
        return new PageInfo<>(list);
    }

    @Override
    public List<StatisticDto> sexRatio() {
        List<StatisticDto> statisticDtoList = oauthUserMapper.sexRatio();
        return statisticDtoList;
    }

    @Override
    public UserEntity findEntityByPhone(String phone) {
        OauthUser oauthUser = oauthUserRepository.findByPhone(phone);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(oauthUser, userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findEntityByUsername(String username) {
        OauthUser oauthUser = oauthUserRepository.findByUsername(username);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(oauthUser, userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findEntityByEmail(String email) {
        OauthUser oauthUser = oauthUserRepository.findByEmail(email);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(oauthUser, userEntity);
        return userEntity;
    }

    @Override
    public OauthUser registerByPhone(String areaCode, String phone) {
        OauthUser oauthUser = oauthUserRepository.findByPhone(phone);
        if (null == oauthUser){
            oauthUser = new OauthUser();
            oauthUser.setAreaCode(areaCode);
            oauthUser.setPhone(phone);
            oauthUser.setEnabled(true);
            oauthUser.setUsername(UsernameUtil.generateByPhone(phone));
            oauthUser = oauthUserRepository.save(oauthUser);
            return oauthUser;
        }else{
            throw new UserExistsException("用户已存在，请登录");
        }
    }

    @Override
    public OauthUser registerByAccountAndPwd(String account, String password) {
        if(ValidUtil.isMobile(account)){
            //account是手机号

        }else if(ValidUtil.isEmail(account)){
            //account是邮箱
        }else{

        }
        return null;
    }

    @Override
    public OauthUser registerByPhoneAndPwd(String phone, String password) {
        OauthUser oauthUser = oauthUserRepository.findByPhone(phone);
        if (null == oauthUser){
            oauthUser = new OauthUser();
            oauthUser.setUsername(UsernameUtil.generateByPhone(phone));
            oauthUser.setPassword(passwordEncoder.encode(password));
            oauthUser.setEnabled(true);
            oauthUser.setPhone(phone);
            oauthUser = oauthUserRepository.save(oauthUser);
            return oauthUser;
        }else{
            throw new UserExistsException("用户已存在，请登录");
        }
    }

    @Override
    public OauthUser registerByEmailAndPwd(String email, String password) {
        OauthUser oauthUser = oauthUserRepository.findByEmail(email);
        if (null == oauthUser){
            oauthUser = new OauthUser();
            oauthUser.setUsername(UsernameUtil.generateByEmail(email));
            oauthUser.setPassword(passwordEncoder.encode(password));
            oauthUser.setEnabled(true);
            oauthUser.setEmail(email);
            oauthUser = oauthUserRepository.save(oauthUser);
            return oauthUser;
        }else{
            throw new UserExistsException("用户已存在，请登录");
        }
    }

    @Override
    public OauthUser registerByUsernameAndPwd(String username, String password) {
        OauthUser oauthUser = oauthUserRepository.findByUsername(username);
        if (null == oauthUser){
            oauthUser = new OauthUser();
            oauthUser.setUsername(username);
            oauthUser.setPassword(passwordEncoder.encode(password));
            oauthUser.setEnabled(true);
            oauthUser = oauthUserRepository.save(oauthUser);
            return oauthUser;
        }else{
            throw new UserExistsException("用户已存在，请登录");
        }
    }

    @Override
    public List<AuthorityDto> getUnauthorized() {
        return oauthUserMapper.getUnauthorized();
    }

    @Override
    public int countByPhoneOrEmail(String phone, String email) {
        return oauthUserMapper.countByPhoneOrEmail(phone, email);
    }

    @Override
    public int updatePasswordByUserId(Long userId, String newPassword) {
        return oauthUserMapper.updatePasswordByUserId(userId, newPassword);
    }
}