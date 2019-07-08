package com.simon.service;

import com.simon.common.domain.UserEntity;
import com.simon.common.service.BasicService;
import com.simon.dto.AuthorityDto;
import com.simon.model.OauthUser;

import java.util.List;

/**
 * 用户
 *
 * @author simon
 * @create 2018-07-31 19:55
 **/

public interface OauthUserService extends BasicService<OauthUser, Long> {
    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return UserEntity实体
     */
    UserEntity findEntityByPhone(String phone);

    /**
     * 根据手机号注册
     * @param areaCode 手机区号
     * @param phone 手机号
     * @return 注册账号信息
     */
    OauthUser registerByPhone(String areaCode, String phone);

    /**
     * 根据手机号和密码注册
     * @param areaCode 手机区号
     * @param phone 手机号
     * @param password 密码
     * @return 注册信息
     */
    OauthUser registerByPhoneAndPassword(String areaCode, String phone, String password);

    /**
     * 根据邮箱和密码注册
     * @param email 邮箱
     * @param password 密码
     * @return 注册信息
     */
    OauthUser registerByEmailAndPassword(String email, String password);

    /**
     * 根据用户名和密码注册
     * @param username 用户名
     * @param password 密码
     * @return 注册信息
     */
    OauthUser registerByUsernameAndPassword(String username, String password);

    /**
     * 根据手机号和验证码重置密码
     * @param phone 手机号
     * @param code 验证码
     * @param password 新密码
     * @return 影响行数
     */
    int updatePwdByPhoneAndCode(String phone, String code, String password);

    /**
     * 根据用户名和旧密码更新密码
     * @param username 用户名
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 影响行数
     */
    int updatePwdByOldPwd(String username, String oldPwd, String newPwd);

    /**
     * 获取未配置角色的用户
     * @return 未配置角色的用户列表
     */
    List<AuthorityDto> getUnauthorized();

    /**
     * 根据手机号或者邮箱登录
     * @param phone 手机号
     * @param email 邮箱
     * @return 记录个数
     */
    int countByPhoneOrEmail(String phone, String email);

    /**
     * 根据用户id更新密码
     * @param userId 用户id
     * @param newPassword 新密码
     * @return 影响行数
     */
    int updatePasswordByUserId(Long userId, String newPassword);
}
