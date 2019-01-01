package ${basePackage}.controller;

import ${basePackage}.common.controller.BaseController;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;

import ${basePackage}.common.domain.ResultMsg;
import ${basePackage}.common.domain.EasyUIDataGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
public class ${entityName}Controller extends BaseController{

    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    @ApiOperation(value = "新增")
    @PostMapping
    @ResponseBody
    public ResultMsg add(@RequestBody ${entityName} body){
        ${entityName?uncap_first}Service.save(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "修改")
    @PatchMapping
    @ResponseBody
    public ResultMsg update(@RequestBody ${entityName} body){
        ${entityName?uncap_first}Service.updateByPrimaryKeySelective(body);
        return ResultMsg.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/id/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids){
        ${entityName?uncap_first}Service.deleteByIds(ids);
        return ResultMsg.success();
    }
}