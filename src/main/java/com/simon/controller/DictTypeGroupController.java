package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.service.DictTypeGroupService;
import com.simon.service.DictTypeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-09-23
**/

@Slf4j
@Api(description = "字典")
@Controller
@RequestMapping("dictTypeGroups")
public class DictTypeGroupController extends BaseController{

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private DictTypeGroupService dictTypeGroupService;

    @GetMapping("data")
    @ResponseBody
    public Map<String, Object> list(
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset){
        Map<String, Object> resultMap = new LinkedHashMap<>(2);
        resultMap.put("total", dictTypeGroupService.count());
        resultMap.put("rows", dictTypeGroupService.getDtos(limit, offset));
        return resultMap;
    }

    @GetMapping(params = "list")
    public String getList(){
        return "dict_type";
    }

    @GetMapping(params = "easyui-list")
    public String getEasyUIList(){
        return "easyui/dict_type";
    }
}