package com.simon.controller;

import com.alibaba.fastjson.JSON;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.common.domain.UserEntity;
import com.simon.common.utils.BeanUtils;
import com.simon.model.OauthUser;
import com.simon.service.DictTypeService;
import com.simon.service.OauthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* 用户表
*
* @author SimonSun
* @date 2019-01-22
**/
@Slf4j
@Api(description = "用户表")
@Controller
@RequestMapping("/api/oauthUsers")
public class OauthUserController extends BaseController{

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private DictTypeService dictTypeService;

    @ApiIgnore
    @ApiOperation(value = "列表页面")
    @GetMapping("list")
    public String list(Model model){
        model.addAttribute("sexTypeList", dictTypeService.getTypeByGroupCode("sex_type"));
        model.addAttribute("loginTypeList", dictTypeService.getTypeByGroupCode("login_type"));
        model.addAttribute("loginStatusList", dictTypeService.getTypeByGroupCode("login_status"));
        model.addAttribute("enabledStatusList", dictTypeService.getTypeByGroupCode("enabled_status"));
        return "easyui/oauthUser/list";
    }

    @ApiIgnore
    @ApiOperation(value = "新增页面")
    @GetMapping("add")
    public String add(){
        return "easyui/oauthUser/add";
    }

    @ApiIgnore
    @ApiOperation(value = "编辑页面")
    @GetMapping("edit")
    public String edit(@RequestParam Long id, Model model){
        model.addAttribute("oauthUser", oauthUserService.findById(id));
        return "easyui/oauthUser/edit";
    }

    @ApiIgnore
    @ApiOperation(value = "列表数据")
    @GetMapping("data")
    @ResponseBody
    public EasyUIDataGridResult<OauthUser> data(
            @ApiParam(value= "用户名")@RequestParam(required = false) String username,
            @ApiParam(value= "有效")@RequestParam(required = false) Boolean enabled,
            @ApiParam(value= "手机号")@RequestParam(required = false) String phone,
            @ApiParam(value= "邮箱")@RequestParam(required = false) String email,
            @ApiParam(value= "性别")@RequestParam(required = false) Boolean sex,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", username);
        params.put("enabled", enabled);
        params.put("phone", phone);
        params.put("email", email);
        params.put("sex", sex);
        return new EasyUIDataGridResult<>(oauthUserService.getList(params, pageNo, pageSize, orderBy));
    }

    @ApiOperation(value = "新增")
    @PostMapping("add")
    @ResponseBody
    public ResultMsg add(@RequestBody OauthUser body, Authentication authentication){
        Object principal = authentication.getPrincipal();
        UserEntity userEntity = null;
        if(principal instanceof UserEntity){
            userEntity = (UserEntity)principal;
        }
        body.setCreateDate(new Date());
        body.setCreateBy(userEntity.getId());
        oauthUserService.insertSelective(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "修改")
    @PatchMapping("edit")
    @ResponseBody
    public ResultMsg update(@RequestBody OauthUser body, Authentication authentication){
        Object principal = authentication.getPrincipal();
        UserEntity userEntity = null;
        if(principal instanceof UserEntity){
            userEntity = (UserEntity)principal;
        }
        body.setUpdateDate(new Date());
        body.setUpdateBy(userEntity.getId());
        oauthUserService.updateByPrimaryKeySelective(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids){
        oauthUserService.deleteByIds(ids);
        return ResultMsg.success();
    }

    @ApiIgnore
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "个人中心")
    @GetMapping("/personalCenter")
    public String personalCenter(Model model, Authentication authentication){
        Object principal = authentication.getPrincipal();
        UserEntity userEntity = null;
        if(principal instanceof UserEntity){
            userEntity = (UserEntity)principal;
        }
        if(null != userEntity){
            log.info(JSON.toJSONString(userEntity));
            model.addAttribute("user", userEntity);
        }
        return "easyui/personal_center";
    }

    @ApiOperation(value = "更新个人信息")
    @PatchMapping("personalCenter/edit")
    @ResponseBody
    public ResultMsg updatePersonInfo(@RequestBody OauthUser oauthUser, Authentication authentication){
        oauthUserService.updateByPrimaryKeySelective(oauthUser);

//        log.info("birth=" + new SimpleDateFormat("yyyy-MM-dd").format(oauthUser.getBirth()));
        log.info(JSON.toJSONString(oauthUser));

        Object principal = authentication.getPrincipal();
        UserEntity userEntity = null;
        if(principal instanceof UserEntity){
            userEntity = (UserEntity)principal;
        }
        if(null != userEntity){
            //更新session中的principal
            BeanUtils.copyPropertiesIgnoreNull(oauthUser, userEntity);
        }

        return ResultMsg.success();
    }
}