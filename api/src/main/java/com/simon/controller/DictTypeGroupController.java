package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.dto.DictTypeDto;
import com.simon.service.DictTypeGroupService;
import com.simon.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Locale;

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

    @ApiIgnore
    @PostMapping
    @ResponseBody
    public ResultMsg add(@RequestBody DictTypeDto dictTypeDto, Locale locale){
        if(1 == dictTypeDto.getType()){
            //父字典
            dictTypeGroupService.save(dictTypeDto, locale.toString());
        }else if(2 == dictTypeDto.getType()){
            //子字典
            dictTypeService.save(dictTypeDto, locale.toString());
        }
        return ResultMsg.success();
    }

    @ApiIgnore
    @PatchMapping
    @ResponseBody
    public ResultMsg update(@RequestBody DictTypeDto dictTypeDto, Locale locale){
        if(1 == dictTypeDto.getType()){
            //父字典
            dictTypeGroupService.save(dictTypeDto, locale.toString());
        }else if(2 == dictTypeDto.getType()){
            //子字典
            dictTypeService.save(dictTypeDto, locale.toString());
        }
        return ResultMsg.success();
    }

    @ApiIgnore
    @RequestMapping(value = "/checkCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean checkCode(@RequestParam String typeGroupCode){
        if(dictTypeGroupService.countByTypeGroupCode(typeGroupCode) > 0){
            return false;
        }else{
            return true;
        }
    }

    @ApiIgnore
    @DeleteMapping("/type/{type}/id/{id}")
    @ResponseBody
    public ResultMsg delete(
            @ApiParam(value = "字典类型[1:父字典, 2:子字典]", required = true)@PathVariable Integer type,
            @ApiParam(value = "id", required = true)@PathVariable Long id){
        if(1 == type){
            dictTypeGroupService.delete(id);
        }else{
            dictTypeService.delete(id);
        }
        return ResultMsg.success();
    }
}