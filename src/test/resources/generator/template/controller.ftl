package ${basePackage}.controller;

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
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Locale;

/**
* @author ${AUTHOR}
* @create ${CREATE}
**/

@Slf4j
@Api(value = "xx", description = "xx")
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Autowired
    private ${modelNameUpperCamel}Service services;

    @ApiOperation(value = "获取xx列表")
    @RequestMapping(value = "/get_list", method = RequestMethod.POST)
    @ResponseBody
    public Object get_list(@RequestBody BasePageSearch<${modelNameUpperCamel}> modelValid) {
	Condition condition = new Condition(${modelNameUpperCamel}.class);
	Condition.Criteria criteria = condition.createCriteria();
	//        condition.setOrderByClause("ID DESC");

${modelNameUpperCamel} model = modelValid.getSearch() == null?new ${modelNameUpperCamel}():modelValid.getSearch();

	//模型判断是否等于场景使用比如 ID = 10
	//        List<${modelNameUpperCamel}> lists = services.selectForStartPage(model,modelValid.getPage(),modelValid.getLimit());
		//        long total = services.count(model);

		List<${modelNameUpperCamel}> lists = services.selectByConditionForStartPage(condition,modelValid.getPage(),modelValid.getLimit());
			long total = services.countByCondition(condition);

			return new Result(ResultCode.SUCCESS,lists,new PageResult(modelValid.getPage(),total));
    }

    @ApiOperation(value = "获取单条xx")
    @RequestMapping(value = "/get_one", method = RequestMethod.POST)
    @ResponseBody
    public Object get_one(@RequestBody IDValid valid) {
${modelNameUpperCamel} model = services.selectByPrimaryKey(valid.getId());
        return new Result(ResultCode.SUCCESS,model);
        }

        @ApiOperation(value = "新增单条xx")
        @RequestMapping(value = "/add_one", method = RequestMethod.POST)
        @ResponseBody
        public Object add_one(@RequestBody ${modelNameUpperCamel} model) {
        services.insertSelective(model);
        return new Result(ResultCode.SUCCESS,model.getId());
    }

    @ApiOperation(value = "新增多条xx")
    @RequestMapping(value = "/add_multiple", method = RequestMethod.POST)
    @ResponseBody
    public Object add_multiple(@RequestBody List<${modelNameUpperCamel}> model) {
        services.insertList(model);
        return new Result(ResultCode.SUCCESS);
        }

        @ApiOperation(value = "修改单条xx")
        @RequestMapping(value = "/edit_one", method = RequestMethod.POST)
        @ResponseBody
        public Object edit_one(@RequestBody ${modelNameUpperCamel} model) {
        services.updateByPrimaryKeySelective(model);
        return new Result(ResultCode.SUCCESS);
        }

        @ApiOperation(value = "删除单条xx")
        @RequestMapping(value = "/delete_one", method = RequestMethod.POST)
        @ResponseBody
        public Object delete_one(@RequestBody IDValid valid) {
        services.deleteByPrimaryKey(valid.getId());
        return new Result(ResultCode.SUCCESS);
        }

        @ApiOperation(value = "删除多条xx")
        @RequestMapping(value = "/delete_multiple", method = RequestMethod.POST)
        @ResponseBody
        //ids 1,2,3,4
        public Object delete_multiple(@RequestBody String ids) {
        services.deleteByIds(ids);
        return new Result(ResultCode.SUCCESS);
    }
}