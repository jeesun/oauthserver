package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.dto.AuthorityDto;
import com.simon.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

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

    @GetMapping(params = "easyui-list")
    public String getEasyUIList(){
        return "easyui/authority";
    }

    @ApiOperation(value = "easyui列表")
    @GetMapping("easyui/list")
    @ResponseBody
    public EasyUIDataGridResult<AuthorityDto> getEasyUIList(
            @ApiParam(value = "用户id", required = false) @RequestParam(required = false) Long userId,
            @ApiParam(value = "用户名(昵称)", required = false) @RequestParam(required = false) String username,
            @ApiParam(value = "权限", required = false) @RequestParam(required = false) String authority,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("userId", userId);
        params.put("username", username);
        params.put("authority", authority);
        return new EasyUIDataGridResult<>(authorityService.getDtoList(params, pageNo, pageSize, orderBy));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    @ResponseBody
    public ResultMsg add(@RequestBody AuthorityDto authorityDto){
        authorityService.updateByDto(authorityDto);
        return ResultMsg.success();
    }

    @ApiOperation(value = "修改")
    @PatchMapping
    @ResponseBody
    public ResultMsg update(@RequestBody AuthorityDto authorityDto){
        authorityService.updateByDto(authorityDto);
        return ResultMsg.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/userIds/{userIds}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String userIds){
        authorityService.deleteByUserIds(userIds);
        return ResultMsg.success();
    }
}