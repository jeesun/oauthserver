package com.simon.controller;

import com.simon.common.config.AppConfig;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.common.domain.UserEntity;
import com.simon.common.utils.BeanUtils;
import com.simon.common.utils.CreateImageCode;
import com.simon.dto.ChangePasswordDto;
import com.simon.model.OauthUser;
import com.simon.service.DictTypeService;
import com.simon.service.OauthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

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
public class OauthUserController extends BaseController {

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiIgnore
    @ApiOperation(value = "vue列表页面")
    @GetMapping("list")
    public String list(Model model, Locale locale) {
        model.addAttribute("sexTypeList", listToMap(dictTypeService.getTypeByGroupCode("sex_type", locale.toString())));
        model.addAttribute("loginTypeList", listToMap(dictTypeService.getTypeByGroupCode("login_type", locale.toString())));
        model.addAttribute("loginStatusList", listToMap(dictTypeService.getTypeByGroupCode("login_status", locale.toString())));
        model.addAttribute("enabledStatusList", listToMap(dictTypeService.getTypeByGroupCode("enabled_status", locale.toString())));
        return "vue/oauthUser/list";
    }

    @ApiIgnore
    @ApiOperation(value = "新增页面")
    @GetMapping("add")
    public String add() {
        return "vue/oauthUser/add";
    }

    @ApiIgnore
    @ApiOperation(value = "编辑页面")
    @GetMapping("edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("entity", entityToMap(oauthUserService.findById(id)));
        return "vue/oauthUser/edit";
    }

    @ApiIgnore
    @ApiOperation(value = "列表数据")
    @GetMapping("data")
    @ResponseBody
    public EasyUIDataGridResult<OauthUser> data(
            @ApiParam(value = "用户名") @RequestParam(required = false) String username,
            @ApiParam(value = "有效") @RequestParam(required = false) Boolean enabled,
            @ApiParam(value = "手机号") @RequestParam(required = false) String phone,
            @ApiParam(value = "邮箱") @RequestParam(required = false) String email,
            @ApiParam(value = "性别") @RequestParam(required = false) Boolean sex,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true) @RequestParam Integer pageSize,
            @ApiParam(value = "排序") @RequestParam(required = false, defaultValue = "") String orderBy) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", username);
        params.put("enabled", enabled);
        params.put("phone", phone);
        params.put("email", email);
        params.put("sex", sex);
        var list = oauthUserService.getList(params, pageNo, pageSize, orderBy);
        //测试element table延时动画
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return new EasyUIDataGridResult<>(list);
    }

    @PermitAll
    @GetMapping("demoData")
    @ResponseBody
    public List<OauthUser> getAll() {
        return oauthUserService.findAll();
    }

    @ApiOperation(value = "新增")
    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<ResultMsg> add(@RequestBody OauthUser body, Authentication authentication) {
        if (oauthUserService.countByPhoneOrEmail(body.getPhone(), body.getEmail()) > 0) {
            return new ResponseEntity<>(ResultMsg.fail(ResultCode.FAIL_PHONE_OR_EMAIL_EXISTS), HttpStatus.CONFLICT);
        }

        UserEntity userEntity = getCurrentUser(authentication);
        body.setCreateDate(LocalDateTime.now());
        body.setCreateBy(userEntity.getId());
        oauthUserService.insertSelective(body);
        return new ResponseEntity<>(ResultMsg.success(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改")
    @PatchMapping("edit")
    @ResponseBody
    public ResultMsg update(@RequestBody OauthUser body, Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        body.setUpdateDate(LocalDateTime.now());
        body.setUpdateBy(userEntity.getId());
        oauthUserService.updateByPrimaryKeySelective(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids) {
        oauthUserService.deleteByIds(ids);
        return ResultMsg.success();
    }

    @ApiIgnore
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "个人中心")
    @GetMapping("/userCenter")
    public String userCenter(Model model, Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        if (null != userEntity) {
            model.addAttribute("entity", entityToMap(userEntity));
        }
        return "vue/oauthUser/user_center";
    }

    @ApiOperation(value = "更新个人信息")
    @PatchMapping("/userCenter")
    @ResponseBody
    public ResultMsg updatePersonInfo(@RequestBody OauthUser oauthUser, Authentication authentication) {
        oauthUserService.updateByPrimaryKeySelective(oauthUser);

        Object principal = authentication.getPrincipal();
        UserEntity userEntity = null;
        if (principal instanceof UserEntity) {
            userEntity = (UserEntity) principal;
        }
        if (null != userEntity) {
            //更新session中的principal
            BeanUtils.copyPropertiesIgnoreNull(oauthUser, userEntity);
        }

        return ResultMsg.success();
    }

    @ApiIgnore
    @ApiOperation(value = "导出")
    @GetMapping("export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

    }

    @ApiIgnore
    @ApiOperation(value = "导入")
    @GetMapping("import")
    @ResponseBody
    public ResultMsg importExcel() {
        return ResultMsg.success();
    }

    @ApiIgnore
    @PostMapping("/changePassword")
    @ResponseBody
    public ResponseEntity<ResultMsg> changePassword(@RequestBody ChangePasswordDto body, Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        if (!passwordEncoder.matches(body.getOldPassword(), userEntity.getPassword())) {
            return new ResponseEntity<>(ResultMsg.fail(ResultCode.FAIL_INCORRECT_PASSWORD), HttpStatus.NOT_FOUND);
        }
        oauthUserService.updatePasswordByUserId(userEntity.getId(), passwordEncoder.encode(body.getNewPassword()));

        return new ResponseEntity<>(ResultMsg.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "获取验证码图片", notes = "前端获取session中的值很麻烦，不刷新页面无法获取最新值。所以最好前端再访问后台接口校验验证码图片。")
    @PermitAll
    @GetMapping("getVeriCode")
    public void getCode3(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        CreateImageCode vCode = new CreateImageCode(100, 30, 4, 10);
        session.setAttribute(AppConfig.SESSION_VERI_CODE, vCode.getCode());
        vCode.write(response.getOutputStream());
    }

    @ApiOperation(value = "校验验证码图片")
    @PermitAll
    @PostMapping("checkVeriCode")
    @ResponseBody
    public Boolean checkCode(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ApiParam(value = "验证码", required = true) @RequestParam String veriCode) {
        if (veriCode.equals(session.getAttribute(AppConfig.SESSION_VERI_CODE).toString())) {
            return true;
        }
        return false;
    }
}