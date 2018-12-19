package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.dto.AuthorityDto;
import com.simon.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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