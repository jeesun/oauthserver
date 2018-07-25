package com.simon.controller;

import com.simon.domain.ResultMsg;
import com.simon.model.OauthUser;
import com.simon.model.UserInfo;
import com.simon.repository.OauthUserRepository;
import com.simon.repository.UserInfoRepository;
import com.simon.common.utils.ImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by simon on 2016/8/21.
 */
@Api(value="用户信息", description = "用户信息")
@RestController
@RequestMapping("/api/userInfos")
public class UserInfoController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private OauthUserRepository oauthUserRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ResourceLoader resourceLoader;

    private static final String ROOT = "appUsers";

    @Autowired
    public UserInfoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value="根据access token获取用户信息")
    @RequestMapping(value = "/accessToken",method = RequestMethod.GET)
    public ResultMsg getUserInfoByAccessToken(@RequestParam String access_token){
        logger.info(access_token);
        String username = getUsernameByAccessToken(access_token);
        logger.warn("username = " + username);
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
