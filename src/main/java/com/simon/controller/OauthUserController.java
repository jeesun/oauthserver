package com.simon.controller;

import com.github.pagehelper.PageInfo;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.common.exception.UserNotValidException;
import com.simon.model.OauthUser;
import com.simon.service.OauthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author simon
 * @date 2016/8/21
 */
@Slf4j
@Api(description = "用户信息")
@Controller
@RequestMapping("/api/oauthUsers")
public class OauthUserController extends BaseController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OauthUserService oauthUserService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    @ApiOperation(value = "获取个人信息")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg getUserInfo(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        if(null == principal){
            throw new UserNotValidException();
        }
        return ResultMsg.success(200, "", oauthUserService.findByUsername(principal.getName()));
    }

    @ApiOperation(value = "获取个人信息test")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg getUserInfo(@RequestParam String username){
        return ResultMsg.success(200, "", oauthUserService.findByUsername(username));
    }

    @ApiOperation(value = "注册", notes = "注册成功返回appUser对象，包含自动生成的username", httpMethod = "POST")
    @RequestMapping(value = "/registerWithVeriCode",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg post(@RequestParam(required = false) Integer code, @RequestParam String phone, @RequestParam String password) {
        oauthUserService.register(code, phone, password);
        return ResultMsg.success(201, "注册成功");
    }

    @ApiOperation(value = "更新密码（使用旧密码）", notes = "")
    @RequestMapping(value = "/updatePassword/{oldPassword}/{newPassword}", method = RequestMethod.PATCH)
    @ResponseBody
    public ResultMsg updatePassword(@RequestParam String phone, @ApiParam(value = "旧密码", required = true) @PathVariable String oldPassword, @PathVariable String newPassword){
        oauthUserService.updatePwdByOldPwd(phone, oldPassword, newPassword);
        return ResultMsg.success(200, "密码更新成功");
    }

    @ApiOperation(value = "更新密码（使用手机验证码）")
    @RequestMapping(value = "/updatePwdWithoutOldPwd", method = RequestMethod.PATCH)
    @ResponseBody
    public ResultMsg updatePwdWithoutOldPwd(@RequestParam String phone, @RequestParam Integer code, @RequestParam String newPwd){
        oauthUserService.updatePwdByCode(phone, code, newPwd);
        return ResultMsg.success(200, "密码更新成功");
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public ResultMsg patch(HttpServletRequest request,
                           @ApiParam(value = "用户") @RequestBody OauthUser oauthUser){
        Principal principal = request.getUserPrincipal();
        Locale locale = request.getLocale();
        if(null == principal){
            //throw new UserNotValidException(messageSource.getMessage("userNotValid", null, locale));
            //判断是不是管理员
        }

        return ResultMsg.success(200, "更新用户信息成功", oauthUserService.save(oauthUser));
    }

    @ApiOperation(value = "easyui列表")
    @GetMapping(value = "easyui/list")
    @ResponseBody
    public EasyUIDataGridResult<OauthUser> getEasyUiList(
            @ApiParam(value = "用户名(昵称)") @RequestParam(required = false) String username,
            @ApiParam(value = "手机号") @RequestParam(required = false) String phone,
            @ApiParam(value = "邮箱") @RequestParam(required = false) String email,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new HashMap<>(3);
        params.put("username", username);
        params.put("email", email);
        params.put("phone", phone);
        PageInfo<OauthUser> pageInfo = oauthUserService.getList(params, pageSize, (pageNo - 1) * pageSize, orderBy);
        return new EasyUIDataGridResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping(params = "easyui-list")
    public String easyUiList(){
        return "easyui/oauth_user";
    }

    @PostMapping(value = "")
    @ResponseBody
    public ResultMsg add(@RequestBody OauthUser body){
        body.setPassword(passwordEncoder.encode(body.getPassword()));
        oauthUserService.save(body);
        return ResultMsg.success();
    }

    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids){
        oauthUserService.deleteByIds(ids);
        return ResultMsg.success();
    }
}
