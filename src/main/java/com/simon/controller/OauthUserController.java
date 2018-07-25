package com.simon.controller;

import com.simon.domain.ResultMsg;
import com.simon.model.Authority;
import com.simon.model.OauthUser;
import com.simon.model.UserInfo;
import com.simon.model.VeriCode;
import com.simon.repository.AuthorityRepository;
import com.simon.repository.OauthUserRepository;
import com.simon.repository.UserInfoRepository;
import com.simon.repository.VeriCodeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by simon on 2016/8/16.
 */
@Slf4j
@Api(value="登录注册", description = "登录注册")
@RestController
@RequestMapping("/api/oauthUsers")
public class OauthUserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private VeriCodeRepository veriCodeRepository;

    @Autowired
    private OauthUserRepository oauthUserRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @ApiOperation(value = "注册", notes = "注册成功返回appUser对象，包含自动生成的username", httpMethod = "POST")
    @RequestMapping(value = "/registerWithVeriCode",method = RequestMethod.POST)
    public ResultMsg post(@RequestParam(required = false) Integer code, @RequestParam String phone, @RequestParam String password) {
        //加密密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        password = encoder.encode(password);

        if(null != code){
            VeriCode veriCode = veriCodeRepository.findByPhoneAndCode(phone, code);
            if (null!=veriCode){
                return register(phone, password);
            }else{
                return new ResultMsg(404, "验证码错误或者过期");
            }
        }else{
            return register(phone, password);
        }
    }

    @Transactional//事务加在接口方法上，会造成JpaRepository未注入。加在普通方法上没有问题。
    protected ResultMsg register(String phone, String password){
        //判断username是否存在
        try {
            OauthUser oauthUser = new OauthUser();
            oauthUser.setUsername("user" + phone.substring(phone.length()-4, phone.length()));
            oauthUser.setPhone(phone);
            oauthUser.setPassword(password);
            oauthUser.setEnabled(true);
            oauthUser = oauthUserRepository.save(oauthUser);

            Authority authority = new Authority();
            authority.setUserId(oauthUser.getId());
            authority.setAuthority("ROLE_USER");
            authority = authorityRepository.save(authority);

            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(oauthUser.getId());
            userInfo.setPhone(phone);
            userInfo.setUsername(oauthUser.getUsername());

            return new ResultMsg(201, "注册成功", userInfoRepository.save(userInfo));
        } catch (DataIntegrityViolationException e) {
            return new ResultMsg(409, "用户名已存在", e.getMessage());
        }
    }

    @ApiOperation(value = "更新密码（使用旧密码）", notes = "")
    @RequestMapping(value = "/updatePassword/{oldPassword}/{newPassword}", method = RequestMethod.PATCH)
    public ResultMsg updatePassword(Principal principal, @ApiParam(value = "旧密码", required = true) @PathVariable String oldPassword, @PathVariable String newPassword){
        OauthUser oauthUser = oauthUserRepository.findByUsername(principal.getName());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

        if (null!=oauthUser){
            if(encoder.matches(oldPassword, oauthUser.getPassword())){
                oauthUser.setPassword(encoder.encode(newPassword));
                oauthUserRepository.save(oauthUser);
                return ResultMsg.success(201, "修改密码成功");
            }else{
                return new ResultMsg(404, "旧密码错误");
            }
        }else {
            return new ResultMsg(404, "手机号尚未注册");
        }
    }

    @ApiOperation(value = "更新密码（使用手机验证码）",notes = "此处还需要传一次验证码，防止有人破解app后知道更新密码api，直接更新其他用户密码")
    @RequestMapping(value = "/updatePwdWithoutOldPwd", method = RequestMethod.PATCH)
    public ResultMsg updatePwdWithoutOldPwd(@RequestParam String phone, @RequestParam Integer code, @RequestParam String newPwd){
        //加密密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        newPwd = encoder.encode(newPwd);

        try{
            VeriCode veriCode = veriCodeRepository.findByPhoneAndCode(phone, code);
            if (null != veriCode){
                this.jdbcTemplate.update("UPDATE users SET password = ? WHERE phone = ?", newPwd, phone);
                return new ResultMsg(200, "更新密码成功");
            }else{
                return new ResultMsg(404, "验证码过期，更新密码失败");
            }
        }catch (Exception e){
            return new ResultMsg(404, "更新密码失败", e.getMessage());
        }
    }
}
