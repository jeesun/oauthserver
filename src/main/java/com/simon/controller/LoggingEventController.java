package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.model.LoggingEvent;
import com.simon.service.LoggingEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-11-09
**/
@Slf4j
@Api(description = "日志记录")
@Controller
@RequestMapping("/api/loggingEvents")
public class LoggingEventController extends BaseController{

    @Autowired
    private LoggingEventService loggingEventService;

    @GetMapping(params = "easyui-list")
    public String easyUIList(){
        return "easyui/logging_event";
    }

    @GetMapping("/easyui/list")
    @ResponseBody
    public EasyUIDataGridResult<LoggingEvent> getEasyUIList(
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序(eg: id desc)")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new LinkedHashMap<>();

        return new EasyUIDataGridResult<>(loggingEventService.getList(params, pageSize, (pageNo - 1) * pageSize, orderBy));
    }

    @ApiOperation(value = "清空")
    @DeleteMapping
    @ResponseBody
    public ResultMsg empty(){
        loggingEventService.deleteAll();
        return ResultMsg.success();
    }
}