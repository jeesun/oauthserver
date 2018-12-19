package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.dto.DictTypeDto;
import com.simon.dto.EasyUiTreeGridDto;
import com.simon.model.DictType;
import com.simon.service.DictTypeGroupService;
import com.simon.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;
import java.util.List;
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

    @ApiIgnore
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

    @ApiIgnore
    @GetMapping(params = "list")
    public String getList(){
        return "dict_type";
    }

    @ApiIgnore
    @GetMapping(params = "easyui-list")
    public String getEasyUIList(){
        return "easyui/dict_type";
    }

    @ApiIgnore
    @GetMapping("easyui/list")
    @ResponseBody
    public EasyUIDataGridResult<EasyUiTreeGridDto> getEasyUIListData(
            @ApiParam(value = "父字典名称", required = false) @RequestParam(required = false) String name,
            @ApiParam(value = "父字典编码", required = false) @RequestParam(required = false) String code,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序(eg: id desc)")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("name", name);
        params.put("code", code);
        var pageInfo = dictTypeGroupService.getTreeGridDtos(params, pageNo, pageSize, orderBy);
        return new EasyUIDataGridResult<>(pageInfo);
    }

    @ApiIgnore
    @PostMapping
    @ResponseBody
    public ResultMsg add(@RequestBody DictTypeDto dictTypeDto){
        if(1 == dictTypeDto.getType()){
            //父字典
            dictTypeGroupService.save(dictTypeDto);
        }else if(2 == dictTypeDto.getType()){
            //子字典
            dictTypeService.save(dictTypeDto);
        }
        return ResultMsg.success();
    }

    @ApiIgnore
    @PatchMapping
    @ResponseBody
    public ResultMsg update(@RequestBody DictTypeDto dictTypeDto){
        if(1 == dictTypeDto.getType()){
            //父字典
            dictTypeGroupService.save(dictTypeDto);
        }else if(2 == dictTypeDto.getType()){
            //子字典
            dictTypeService.save(dictTypeDto);
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

    @GetMapping("/typeGroupCode/{typeGroupCode}/dictTypes")
    @ResponseBody
    public List<DictType> getDictTypeByGroupCode(@PathVariable String typeGroupCode){
        return dictTypeService.getTypeByGroupCode(typeGroupCode);
    }
}