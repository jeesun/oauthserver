package com.simon.service;

import com.simon.model.OauthUser;

/**
 * 用户
 *
 * @author simon
 * @create 2018-07-31 19:55
 **/

public interface OauthUserService {
    void register(Integer code, String phone, String password);
    void register(String phone, String password);
    int updatePwdByCode(String phone, Integer code, String newPwd);
    int updatePwdByOldPwd(String username, String oldPwd, String newPwd);
    OauthUser findByUsername(String username);
    OauthUser findByPhone(String phone);
    OauthUser save(OauthUser oauthUser);

    int update(String username, String newUsername, String newPhone, String newEmail);
}
