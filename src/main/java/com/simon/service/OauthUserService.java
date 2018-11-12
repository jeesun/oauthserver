package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.model.OauthUser;

import java.util.Map;

/**
 * 用户
 *
 * @author simon
 * @create 2018-07-31 19:55
 **/

public interface OauthUserService extends BasicService<OauthUser> {
    void register(Integer code, String phone, String password);
    void register(String phone, String password);
    int updatePwdByCode(String phone, Integer code, String newPwd);
    int updatePwdByOldPwd(String username, String oldPwd, String newPwd);
    OauthUser findByUsername(String username);
    OauthUser findByPhone(String phone);
    int update(String username, String newUsername, String newPhone, String newEmail);
    PageInfo<OauthUser> getList(Map<String, Object> params, Integer limit, Integer offset, String orderBy);
}
