package ${basePackage}.controller;

import ${basePackage}.common.controller.BaseController;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;

import ${basePackage}.common.domain.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
* @author ${AUTHOR}
* @date ${CREATE}
**/

@Slf4j
@Api(value = "${tableComment}", description = "${tableComment}", tags = "${entityName?uncap_first}Api")
@Controller
@RequestMapping("${entityName?uncap_first}s")
public class ${entityName}Controller extends BaseController{

    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    @GetMapping("data")
    @ResponseBody
    public Map<String, Object> list(
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset){
        Map<String, Object> resultMap = new LinkedHashMap<>(2);
        resultMap.put("total", ${entityName?uncap_first}Service.count());
        resultMap.put("rows", ${entityName?uncap_first}Service.findAll(new PageRequest(offset/limit, limit, Sort.Direction.DESC, "id")).getContent());
        return resultMap;
    }

    @GetMapping("list")
    public String getList(){
        return "${modelNameLowerCamel}";
    }

    @DeleteMapping("/id/{id}")
    @ResponseBody
    public ResultMsg deleteById(@PathVariable Long id){
        ${entityName?uncap_first}Service.delete(id);
        return ResultMsg.success();
    }

    @PatchMapping
    @ResponseBody
    public ResultMsg updateById(@RequestBody ${entityName} body){
        ${entityName?uncap_first}Service.updateByPrimaryKeySelective(body);
        return ResultMsg.success();
    }

    @PostMapping
    @ResponseBody
    public ResultMsg add(@RequestBody ${entityName} body){
        ${entityName?uncap_first}Service.save(body);
        return ResultMsg.success();
    }
}