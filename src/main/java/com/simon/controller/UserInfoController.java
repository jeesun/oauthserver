package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.common.exception.UserNotValidException;
import com.simon.common.utils.ImageUtil;
import com.simon.model.OauthUser;
import com.simon.service.OauthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Locale;

/**
 * Created by simon on 2016/8/21.
 */
@Slf4j
@Api(value="用户信息", description = "用户信息")
@RestController
@RequestMapping("/api/userInfos")
public class UserInfoController extends BaseController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OauthUserService oauthUserService;

    private final ResourceLoader resourceLoader;

    private static final String ROOT = "appUsers";

    @Autowired
    public UserInfoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value = "获取个人信息")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultMsg getUserInfo(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        if(null == principal){
            throw new UserNotValidException();
        }
        return ResultMsg.success(200, "", oauthUserService.findByUsername(principal.getName()));
    }

    @ApiOperation(value = "注册", notes = "注册成功返回appUser对象，包含自动生成的username", httpMethod = "POST")
    @RequestMapping(value = "/registerWithVeriCode",method = RequestMethod.POST)
    public ResultMsg post(@RequestParam(required = false) Integer code, @RequestParam String phone, @RequestParam String password) {
        oauthUserService.register(code, phone, password);
        return ResultMsg.success(201, "注册成功");
    }

    @ApiOperation(value = "更新密码（使用旧密码）", notes = "")
    @RequestMapping(value = "/updatePassword/{oldPassword}/{newPassword}", method = RequestMethod.PATCH)
    public ResultMsg updatePassword(@RequestParam String phone, @ApiParam(value = "旧密码", required = true) @PathVariable String oldPassword, @PathVariable String newPassword){
        oauthUserService.updatePwdByOldPwd(phone, oldPassword, newPassword);
        return ResultMsg.success(200, "密码更新成功");
    }

    @ApiOperation(value = "更新密码（使用手机验证码）")
    @RequestMapping(value = "/updatePwdWithoutOldPwd", method = RequestMethod.PATCH)
    public ResultMsg updatePwdWithoutOldPwd(@RequestParam String phone, @RequestParam Integer code, @RequestParam String newPwd){
        oauthUserService.updatePwdByCode(phone, code, newPwd);
        return ResultMsg.success(200, "密码更新成功");
    }

    @ApiOperation(value = "更新头像")
    @RequestMapping(value = "/updateHeadPhoto", method = RequestMethod.PATCH)
    public ResultMsg updateHeadPhoto(HttpServletRequest request, @RequestParam String photoBase64){
        Principal principal = request.getUserPrincipal();
        if(null == principal){
            throw new UserNotValidException();
        }
        var username = principal.getName();
        var oauthUser = oauthUserService.findByUsername(username);
        var headPhotoUrl = ROOT+"/"+oauthUser.getId() + "/" + System.currentTimeMillis() + ".png";
        var headPhotoDir = ROOT + "/" + oauthUser.getId();
        try{
            if (!Files.exists(Paths.get(headPhotoDir))){
                Files.createDirectories(Paths.get(headPhotoDir));
                if (!Files.exists(Paths.get(headPhotoUrl))){
                    Files.createFile(Paths.get(headPhotoUrl));
                }
            }

            Files.write(Paths.get(headPhotoUrl), ImageUtil.convertToBytes(photoBase64));
            oauthUser.setHeadPhoto(headPhotoUrl);
            return ResultMsg.success(200, "更新头像成功", oauthUserService.save(oauthUser));
        }catch (IOException e){
            return ResultMsg.fail(404, "创建文件夹或者文件失败", e.getMessage());
        }catch (Exception e){
            return ResultMsg.fail(500, "未知错误", e.getMessage());
        }
    }

    @ApiOperation(value = "获取头像")
    @RequestMapping(value = "/{baseFolder}/{phoneFolder}/{fileName:.+}", method = RequestMethod.GET)
    public ResponseEntity<?> getFile(@PathVariable("baseFolder")String root, @PathVariable("phoneFolder")String phoneFolder, @PathVariable("fileName")String fileName){
        try{
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(root+"/"+phoneFolder, fileName).toString()));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ResultMsg patch(HttpServletRequest request,
                           @ApiParam(value = "用户") @RequestBody OauthUser oauthUser){
        Principal principal = request.getUserPrincipal();
        Locale locale = request.getLocale();
        if(null == principal){
            throw new UserNotValidException(messageSource.getMessage("userNotValid", null, locale));
        }

        return ResultMsg.success(200, "更新用户信息成功", oauthUserService.save(oauthUser));
    }
}
