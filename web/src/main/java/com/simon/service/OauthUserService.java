package com.simon.service;

import com.simon.common.domain.UserEntity;
import com.simon.common.service.BasicService;
import com.simon.dto.StatisticDto;
import com.simon.model.OauthUser;

import java.util.List;

/**
 * 用户
 *
 * @author simon
 * @create 2018-07-31 19:55
 **/

public interface OauthUserService extends BasicService<OauthUser, Long> {
    void register(Integer code, String phone, String password);

    void register(String phone, String password);

    int updatePwdByCode(String phone, Integer code, String newPwd);

    int updatePwdByOldPwd(String username, String oldPwd, String newPwd);

    List<StatisticDto> sexRatio();

    UserEntity findEntityByPhone(String phone);
    UserEntity findEntityByUsername(String username);
    UserEntity findEntityByEmail(String email);

    /**
     * 根据手机号注册
     * @param phone 手机号
     * @return 注册账号信息
     */
    OauthUser registerByPhone(String phone);

    /**
     * 根据账号(用户名、手机号、邮箱)+密码注册
     * @param account
     * @param password
     * @return
     */
    OauthUser registerByAccountAndPwd(String account, String password);

    /**
     * 根据手机号+密码注册
     * @param phone 手机号
     * @param password 密码
     * @return 注册账号信息
     */
    OauthUser registerByPhoneAndPwd(String phone, String password);

    /**
     * 根据邮箱+密码注册
     * @param email 邮箱
     * @param password 密码
     * @return 注册账号信息
     */
    OauthUser registerByEmailAndPwd(String email, String password);

    /**
     * 根据用户名+密码注册
     * @param username 用户名
     * @param password 密码
     * @return 注册账号信息
     */
    OauthUser registerByUsernameAndPwd(String username, String password);
}
