package com.simon.controller;

import com.simon.domain.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author simon
 * @create 2018-05-31 10:10
 **/
@Api("测试")
@RestController
@RequestMapping("/api/helloWorlds")
public class HelloWorldController {

    @ApiOperation("测试")
    @GetMapping(value = "")
    public ResultMsg test(@RequestParam String access_token){
        return ResultMsg.success(200, "");
    }
}
