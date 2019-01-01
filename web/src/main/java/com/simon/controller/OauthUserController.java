package com.simon.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.common.domain.UserEntity;
import com.simon.common.utils.BeanUtils;
import com.simon.dto.StatisticDto;
import com.simon.model.OauthUser;
import com.simon.service.OauthUserService;
import com.simon.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private SmsService smsService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    @ApiIgnore
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

    @ApiIgnore
    @GetMapping(params = "easyui-list")
    public String easyUiList(){
        return "easyui/oauth_user";
    }

    @PostMapping(value = "")
    @ResponseBody
    public ResultMsg add(@RequestBody OauthUser body){
        oauthUserService.save(body);
        return ResultMsg.success();
    }

    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids){
        oauthUserService.deleteByIds(ids);
        return ResultMsg.success();
    }

    @PermitAll
    @GetMapping("/statistics/sex")
    @ResponseBody
    public ResultMsg<List<StatisticDto>> sexRatio(){
        return ResultMsg.success(oauthUserService.sexRatio());
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
    @PatchMapping
    @ResponseBody
    public ResultMsg update(@RequestBody OauthUser oauthUser, Authentication authentication){
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
