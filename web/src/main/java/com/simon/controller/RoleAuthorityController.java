package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.dto.EasyUiTreeDto;
import com.simon.model.DictType;
import com.simon.request.SideMenuAuthorityRequest;
import com.simon.service.DictTypeService;
import com.simon.service.SideMenuAuthorityService;
import com.simon.service.SideMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 角色权限
 *
 * @author simon
 * @date 2019-01-12
 **/
@Slf4j
@ApiIgnore
@Api(description = "角色权限")
@Controller
@RequestMapping("/api/roleAuthorities")
public class RoleAuthorityController extends BaseController {
    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private SideMenuService sideMenuService;

    @Autowired
    private SideMenuAuthorityService sideMenuAuthorityService;

    @ApiIgnore
    @ApiOperation(value = "列表页面")
    @GetMapping("list")
    public String list(Model model){
        return "easyui/roleAuthority/list";
    }

    @ApiIgnore
    @ApiOperation(value = "列表数据")
    @GetMapping("data")
    @ResponseBody
    public List<DictType> data(
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true) @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "排序") @RequestParam(required = false, defaultValue = "") String orderBy) {
        return dictTypeService.getTypeByGroupCode("role_type");
    }

    @GetMapping("authConfig")
    public String authConfig(@RequestParam String typeCode, Model model){
        model.addAttribute("typeCode", typeCode);
        return "easyui/roleAuthority/auth_config";
    }

    @GetMapping("authData")
    @ResponseBody
    public List<EasyUiTreeDto> getAuthData(@RequestParam String typeCode){
        return sideMenuService.getAuth(typeCode);
    }

    @PostMapping("updateAuth")
    @ResponseBody
    public ResultMsg updateAuth(@RequestBody SideMenuAuthorityRequest body){
        sideMenuAuthorityService.updateAuth(body.getIds(), body.getAuthority());
        return ResultMsg.success();
    }
}
