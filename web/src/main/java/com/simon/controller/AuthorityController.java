package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.dto.AuthorityDto;
import com.simon.service.AuthorityService;
import com.simon.service.DictTypeService;
import com.simon.service.OauthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Locale;

/**
 * 权限
 *
 * @author simon
 * @date 2018-11-14
 **/
@Slf4j
@ApiIgnore
@Api(description = "权限")
@Controller
@RequestMapping("/api/authorities")
public class AuthorityController extends BaseController {
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private DictTypeService dictTypeService;

    @GetMapping("list")
    public String list(Model model, Locale locale) {
        model.addAttribute("roleTypeList", dictTypeService.getTypeByGroupCode("role_type", locale.toString()));
        return "vue/authority/list";
    }

    @ApiIgnore
    @ApiOperation(value = "新增页面")
    @GetMapping("add")
    public String add(Model model, Locale locale) {
        model.addAttribute("roleTypeList", dictTypeService.getTypeByGroupCode("role_type", locale.toString()));
        return "vue/authority/add";
    }

    @ApiIgnore
    @ApiOperation(value = "编辑页面")
    @GetMapping("edit")
    public String edit(@RequestParam Long userId, Model model, Locale locale) {
        model.addAttribute("roleTypeList", listToMap(dictTypeService.getTypeByGroupCode("role_type", locale.toString())));
        model.addAttribute("entity", entityToMap(authorityService.findDtoByUserId(userId, locale.toString())));
        return "vue/authority/edit";
    }

    @ApiOperation(value = "easyui列表")
    @GetMapping("data")
    @ResponseBody
    public EasyUIDataGridResult<AuthorityDto> getEasyUIList(
            @ApiParam(value = "用户id", required = false) @RequestParam(required = false) Long userId,
            @ApiParam(value = "用户名(昵称)", required = false) @RequestParam(required = false) String username,
            @ApiParam(value = "权限", required = false) @RequestParam(required = false) String authority,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true) @RequestParam Integer pageSize,
            @ApiParam(value = "排序") @RequestParam(required = false, defaultValue = "") String orderBy,
            Locale locale) {
        return new EasyUIDataGridResult<>(authorityService.getDtoList(userId, username, authority, locale.toString(), pageNo, pageSize, orderBy));
    }

    @ApiOperation(value = "新增")
    @PostMapping("add")
    @ResponseBody
    public ResultMsg add(@RequestBody AuthorityDto authorityDto) {
        authorityService.updateByDto(authorityDto);
        return ResultMsg.success();
    }

    @ApiOperation(value = "修改")
    @PatchMapping("edit")
    @ResponseBody
    public ResultMsg update(@RequestBody AuthorityDto authorityDto) {
        authorityService.updateByDto(authorityDto);
        return ResultMsg.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/userIds/{userIds}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String userIds) {
        authorityService.deleteByUserIds(userIds);
        return ResultMsg.success();
    }

    @GetMapping("/unauthorized")
    @ResponseBody
    public ResultMsg getUnauthorized() {
        return ResultMsg.success(oauthUserService.getUnauthorized());
    }
}