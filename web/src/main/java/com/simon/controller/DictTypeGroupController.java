package com.simon.controller;

import com.github.pagehelper.PageInfo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author SimonSun
 * @date 2018-09-23
 **/

@Slf4j
@Api(description = "字典")
@Controller
@RequestMapping("/api/dictTypeGroups")
public class DictTypeGroupController extends BaseController {

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private DictTypeGroupService dictTypeGroupService;

    @ApiIgnore
    @GetMapping("data2")
    @ResponseBody
    public Map<String, Object> data2(
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            Locale locale) {
        Map<String, Object> resultMap = new LinkedHashMap<>(2);
        resultMap.put("total", dictTypeGroupService.count());
        resultMap.put("rows", dictTypeGroupService.getDtos(locale.toString(), limit, offset));
        return resultMap;
    }

    @ApiIgnore
    @GetMapping("list")
    public String list(Model model) {
        return "vue/dictType/list";
    }

    @ApiIgnore
    @GetMapping("add")
    public String add(Model model) {
        return "vue/dictType/add";
    }

    @ApiIgnore
    @GetMapping("edit")
    public String edit(Model model, @ApiParam(value = "字典组id", required = true) @RequestParam Long id, Locale locale) {
        model.addAttribute("entity", entityToMap(dictTypeGroupService.getDtoById(id, locale.toString())));
        return "vue/dictType/edit";
    }

    @ApiIgnore
    @GetMapping("subAdd")
    public String subAdd(Model model, @ApiParam(value = "字典组id", required = true) @RequestParam Long id, Locale locale) {
        DictTypeDto dictTypeDto = dictTypeGroupService.getDtoById(id, locale.toString());
        model.addAttribute("entity", entityToMap(dictTypeDto));
        return "vue/dictType/subAdd";
    }

    @ApiIgnore
    @GetMapping("subEdit")
    public String subEdit(Model model, @ApiParam(value = "子字典id", required = true) @RequestParam Long id, Locale locale) {
        DictTypeDto dictTypeDto = dictTypeService.getDtoById(id, locale.toString());
        log.info(id.toString());
        model.addAttribute("entity", entityToMap(dictTypeDto));
        return "vue/dictType/subEdit";
    }

    @ApiIgnore
    @GetMapping("data")
    @ResponseBody
    public EasyUIDataGridResult<EasyUiTreeGridDto> data(
            @ApiParam(value = "父字典名称", required = false) @RequestParam(required = false) String name,
            @ApiParam(value = "父字典编码", required = false) @RequestParam(required = false) String code,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true) @RequestParam Integer pageSize,
            @ApiParam(value = "排序(eg: id desc)") @RequestParam(required = false, defaultValue = "") String orderBy,
            Locale locale) {
        PageInfo<EasyUiTreeGridDto> pageInfo = dictTypeGroupService.getTreeGridDtos(name, code, locale.toString(), pageNo, pageSize, orderBy);
        return new EasyUIDataGridResult<>(pageInfo);
    }

    @ApiIgnore
    @PostMapping("add")
    @ResponseBody
    public ResultMsg add(@RequestBody DictTypeDto dictTypeDto, Locale locale) {
        if (1 == dictTypeDto.getType()) {
            //父字典
            dictTypeGroupService.save(dictTypeDto, locale.toString());
        } else if (2 == dictTypeDto.getType()) {
            //子字典
            dictTypeService.save(dictTypeDto, locale.toString());
        }
        return ResultMsg.success();
    }

    @ApiIgnore
    @PatchMapping("edit")
    @ResponseBody
    public ResultMsg update(@RequestBody DictTypeDto dictTypeDto, Locale locale) {
        if (1 == dictTypeDto.getType()) {
            //父字典
            dictTypeGroupService.save(dictTypeDto, locale.toString());
        } else if (2 == dictTypeDto.getType()) {
            //子字典
            dictTypeService.save(dictTypeDto, locale.toString());
        }
        return ResultMsg.success();
    }

    @DeleteMapping("delete")
    @ResponseBody
    public ResultMsg delete(
            @RequestParam Long id,
            @ApiParam(value = "字典类型[1:父字典, 2:子字典]") @RequestParam Integer type) {
        if (1 == type) {
            dictTypeGroupService.delete(id);
        } else if (2 == type) {
            dictTypeService.delete(id);
        }
        return ResultMsg.success();
    }

    @ApiIgnore
    @RequestMapping(value = "/checkCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean checkCode(@RequestParam String typeGroupCode) {
        if (dictTypeGroupService.countByTypeGroupCode(typeGroupCode) > 0) {
            return false;
        } else {
            return true;
        }
    }

    @ApiIgnore
    @DeleteMapping("/type/{type}/id/{id}")
    @ResponseBody
    public ResultMsg delete(
            @ApiParam(value = "字典类型[1:父字典, 2:子字典]", required = true) @PathVariable Integer type,
            @ApiParam(value = "id", required = true) @PathVariable Long id) {
        if (1 == type) {
            dictTypeGroupService.delete(id);
        } else {
            dictTypeService.delete(id);
        }
        return ResultMsg.success();
    }

    @GetMapping("/typeGroupCode/{typeGroupCode}/dictTypes")
    @ResponseBody
    public List<DictType> getDictTypeByGroupCode(@PathVariable String typeGroupCode, Locale locale) {
        return dictTypeService.getTypeByGroupCode(typeGroupCode, locale.toString());
    }
}