package com.simon.controller;

import com.simon.common.utils.ImageUtil;
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
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

/**
 * Created by simon on 2016/8/21.
 */
@Slf4j
@Api(value="用户信息", description = "用户信息")
@RestController
@RequestMapping("/api/userInfos")
public class UserInfoController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private OauthUserRepository oauthUserRepository;

    @Autowired
    private VeriCodeRepository veriCodeRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ResourceLoader resourceLoader;

    private static final String ROOT = "appUsers";

    @Autowired
    public UserInfoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

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

    @ApiOperation(value="根据access token获取用户信息")
    @RequestMapping(value = "/accessToken",method = RequestMethod.GET)
    public ResultMsg getUserInfoByAccessToken(@RequestParam String access_token){
        log.info(access_token);
        String username = getUsernameByAccessToken(access_token);
        log.warn("username = " + username);
        OauthUser oauthUser = oauthUserRepository.findByUsername(username);
        try{
            return new ResultMsg(200, "获取用户信息成功", userInfoRepository.findByUserId(oauthUser.getId()));
        }catch (DataRetrievalFailureException e) {
            return new ResultMsg(404, "获取用户信息失败", e.getMessage());
        }
    }

    @ApiOperation(value = "更新头像")
    @RequestMapping(value = "/updateHeadPhoto", method = RequestMethod.PATCH)
    private ResultMsg updateHeadPhoto(@RequestParam String access_token, @RequestParam String photoBase64){
        String username = getUsernameByAccessToken(access_token);
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        String headPhotoUrl = ROOT+"/"+userInfo.getUserId() + "/" + System.currentTimeMillis() + ".png";
        String headPhotoDir = ROOT + "/" + userInfo.getUserId();
        try{
            if (!Files.exists(Paths.get(headPhotoDir))){
                Files.createDirectories(Paths.get(headPhotoDir));
                if (!Files.exists(Paths.get(headPhotoUrl))){
                    Files.createFile(Paths.get(headPhotoUrl));
                }
            }

            Files.write(Paths.get(headPhotoUrl), ImageUtil.convertToBytes(photoBase64));
            userInfo.setHeadPhoto(headPhotoUrl);
            return new ResultMsg(200, "更新头像成功", userInfoRepository.save(userInfo));
        }catch (IOException e){
            return new ResultMsg(404, "创建文件夹或者文件失败", e.getMessage());
        }catch (Exception e){
            return new ResultMsg(500, "未知错误", e.getMessage());
        }
    }

    @ApiOperation(value = "获取头像")
    @RequestMapping(value = "/{baseFolder}/{phoneFolder}/{fileName:.+}", method = RequestMethod.GET)
    private ResponseEntity<?> getFile(@PathVariable("baseFolder")String root, @PathVariable("phoneFolder")String phoneFolder, @PathVariable("fileName")String fileName){
        try{
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(root+"/"+phoneFolder, fileName).toString()));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    private ResultMsg patch(@RequestParam String access_token,
                            @RequestParam(required = false) Boolean sex,
                            @RequestParam(required = false) String birth,
                            @RequestParam(required = false) Integer age,
                            @RequestParam(required = false) String personBrief,
                            @RequestParam(required = false) String address){
        String username = getUsernameByAccessToken(access_token);
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if(null != sex){
            userInfo.setSex(sex);
        }
        if(null != birth){
            userInfo.setBirth(birth);
        }
        if(null != age){
            userInfo.setAge(age);
        }
        if(null != personBrief){
            userInfo.setPersonBrief(personBrief);
        }
        if(null != address){
            userInfo.setAddress(address);
        }

        return new ResultMsg(200, "更新用户信息成功", userInfoRepository.save(userInfo));
    }

    @ApiOperation(value = "更新用户信息2")
    @RequestMapping(value = "/update2", method = RequestMethod.PATCH)
    @Transactional
    public ResultMsg update(@RequestParam String access_token,
                            @RequestParam(required = false) String newUsername,
                            @RequestParam(required = false) String newPhone,
                            @RequestParam(required = false) String newEmail){
        String username = getUsernameByAccessToken(access_token);
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        OauthUser oauthUser = oauthUserRepository.findByUsername(username);
        if(null != newUsername){
            userInfo.setUsername(newUsername);
            oauthUser.setUsername(newUsername);
        }
        if(null != newPhone){
            userInfo.setPhone(newPhone);
            oauthUser.setPhone(newPhone);
        }
        if(null != newEmail){
            userInfo.setEmail(newEmail);
            oauthUser.setEmail(newEmail);
        }
        userInfoRepository.save(userInfo);
        oauthUserRepository.save(oauthUser);
        return new ResultMsg(200, "更新成功");
    }


    private String getUsernameByAccessToken(String access_token){
        return jdbcTemplate.queryForObject("SELECT user_name FROM oauth_access_token" +
                " WHERE encode(token, 'escape') LIKE CONCAT('%', ?)", new Object[]{access_token}, String.class);
        /*return jdbcTemplate.queryForObject("SELECT user_name FROM oauth_access_token" +
                " WHERE right(cast(token as char), 36)=?", new Object[]{access_token}, String.class);*/
    }
}
