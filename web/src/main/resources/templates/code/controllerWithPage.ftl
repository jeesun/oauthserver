<#function dashedToCamel(s)>
    <#return s
    ?replace('(^_+)|(_+$)', '', 'r')
    ?replace('\\_+(\\w)?', ' $1', 'r')
    ?replace('([A-Z])', ' $1', 'r')
    ?capitalize
    ?replace(' ' , '')
    ?uncap_first
    >
</#function>
package ${basePackage}.controller;

import com.github.liaochong.myexcel.core.DefaultExcelBuilder;
import com.github.liaochong.myexcel.core.DefaultExcelReader;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import ${basePackage}.common.controller.BaseController;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.DictTypeService;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;

import ${basePackage}.common.domain.ResultMsg;
import ${basePackage}.common.domain.UserEntity;
import ${basePackage}.common.domain.EasyUIDataGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.io.IOException;
import java.util.Locale;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import java.sql.Time;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
* ${tableComment}
*
* @author ${AUTHOR}
* @date ${CREATE}
**/
@Slf4j
@Api(description = "${tableComment}")
@Controller
@RequestMapping("/api/${entityName?uncap_first}s")
public class ${entityName}Controller extends BaseController {

    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    @Autowired
    private DictTypeService dictTypeService;

    @ApiIgnore
    @ApiOperation(value = "列表页面")
    @GetMapping("list")
    public String list(Model model, Locale locale) {
<#list columns as column>
    <#if column.name == "id" || column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
    <#else>
        <#if (column.allowInput?string('yes', 'no'))=='yes' && (column.extraInfo)??>
        model.addAttribute("${column.extraInfo}", dictTypeService.getTypeByGroupCode("${column.extraInfo}", locale.toString()));
        </#if>
    </#if>
</#list>
        return "vue/${entityName?uncap_first}/list";
    }

    @ApiIgnore
    @ApiOperation(value = "新增页面")
    @GetMapping("add")
    public String add(Model model, Locale locale) {
<#list columns as column>
    <#if column.name == "id" || column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
    <#else>
        <#if (column.allowInput?string('yes', 'no'))=='yes' && (column.extraInfo)??>
        model.addAttribute("${column.extraInfo}", dictTypeService.getTypeByGroupCode("${column.extraInfo}", locale.toString()));
        </#if>
    </#if>
</#list>
        return "vue/${entityName?uncap_first}/add";
    }

    @ApiIgnore
    @ApiOperation(value = "编辑页面")
    @GetMapping("edit")
    public String edit(@RequestParam ${idType} id, Model model, Locale locale) {
        model.addAttribute("entity", ${entityName?uncap_first}Service.findById(id));
<#list columns as column>
    <#if column.name == "id" || column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
    <#else>
        <#if (column.allowInput?string('yes', 'no'))=='yes' && (column.extraInfo)??>
        model.addAttribute("${column.extraInfo}", dictTypeService.getTypeByGroupCode("${column.extraInfo}", locale.toString()));
        </#if>
    </#if>
</#list>
        return "vue/${entityName?uncap_first}/edit";
    }

    @ApiIgnore
    @ApiOperation(value = "列表数据")
    @GetMapping("data")
    @ResponseBody
    public EasyUIDataGridResult<${entityName}> data (
<#list columns as column>
    <#if column.allowSearch>
        <#switch column.type>
            <#case "Date">
            @ApiParam(value = "${(column.comment)}-开始时间")@RequestParam(required = false) Date ${column.name}Start,
            @ApiParam(value = "${(column.comment)}-结束时间")@RequestParam(required = false) Date ${column.name}End,
            <#break>
            <#default>
            @ApiParam(value= "${(column.comment)}")@RequestParam(required = false) ${column.type} ${column.name},
        </#switch>
    </#if>
</#list>
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new LinkedHashMap<>();
<#list columns as column>
<#if column.allowSearch>
    <#switch column.type>
        <#case "Date">
        params.put("${column.name}Start", ${column.name}Start);
        params.put("${column.name}End", ${column.name}End);
        <#break>
        <#default>
        params.put("${column.name}", ${column.name});
    </#switch>
</#if>
</#list>
        return new EasyUIDataGridResult<>(${entityName?uncap_first}Service.getList(params, pageNo, pageSize, orderBy));
    }

    @ApiOperation(value = "新增")
    @PostMapping("add")
    @ResponseBody
    public ResultMsg add(@RequestBody ${entityName} body, Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        body.setCreateDate(LocalDateTime.now());
        body.setCreateBy(userEntity.getId());
<#list columns as column>
    <#if column.name == "userId">
        body.setUserId(userEntity.getId());
    </#if>
</#list>
        ${entityName?uncap_first}Service.insertSelective(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "修改")
    @PatchMapping("edit")
    @ResponseBody
    public ResultMsg update(@RequestBody ${entityName} body, Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        body.setUpdateDate(LocalDateTime.now());
        body.setUpdateBy(userEntity.getId());
        ${entityName?uncap_first}Service.updateByPrimaryKeySelective(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete")
    @ResponseBody
    public ResultMsg delete(@RequestParam String ids) {
        ${entityName?uncap_first}Service.deleteByIds(ids);
        return ResultMsg.success();
    }

    @ApiIgnore
    @ApiOperation(value = "导出")
    @GetMapping("export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<${entityName}> list =  ${entityName?uncap_first}Service.findAll();
        Workbook workbook = DefaultExcelBuilder.of(${entityName}.class)
        .build(list);
        AttachmentExportUtil.export(workbook, "${tableComment}", response);
    }

    @ApiIgnore
    @ApiOperation(value = "导入", notes = "该请求路径必须添加到WebSecurityConfig的csrf忽略列表里。")
    @PostMapping("import")
    @ResponseBody
    public ResponseEntity<ResultMsg> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<${entityName}> result = DefaultExcelReader.of(${entityName}.class)
            .sheet(0)
            .rowFilter(row -> row.getRowNum() > 0)
            .read(file.getInputStream());
        ${entityName?uncap_first}Service.batchUpdate(result);
        return ResponseEntity.ok(ResultMsg.success());
    }
}