package com.simon.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.CaseFormat;
import com.simon.common.code.CodeGenerator;
import com.simon.common.code.Column;
import com.simon.common.code.EntityDataModel;
import com.simon.common.code.TableInfo;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.common.domain.UserEntity;
import com.simon.common.utils.DbUtil;
import com.simon.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据表
 *
 * @author simon
 * @date 2018-10-06
 **/
@ApiIgnore
@Api(description = "数据表")
@Slf4j
@Controller
@RequestMapping("/tables")
public class TableController extends BaseController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private DictTypeService dictTypeService;

    @RequestMapping(params = "list", method = RequestMethod.GET)
    public String list(){
        return "table_list";
    }

    @RequestMapping(params = "easyui-list", method = RequestMethod.GET)
    public String easyUiList(){
        return "easyui/table_list";
    }

    @RequestMapping(value = "data", method = RequestMethod.GET)
    @ResponseBody
    public Object getTables(
            @ApiParam(value = "模糊查询表名") @RequestParam(required = false) String tableName,
            @ApiParam(value = "模糊查询表标注") @RequestParam(required = false) String tableComment,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        /*String url = "jdbc:mysql://127.0.0.1:3306/thymelte?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false";
        String user = "root";
        String pwd = "19941017";*/
        /*String url = "jdbc:mysql://47.105.43.147:3306/top_dev?characterEncoding=utf8&useSSL=true";
        String user = "root";
        String pwd = "top@123456";*/
        List<TableInfo> tableInfoList = DbUtil.getTables(CodeGenerator.JDBC_DIVER_CLASS_NAME, CodeGenerator.JDBC_URL, CodeGenerator.JDBC_USERNAME, CodeGenerator.JDBC_PASSWORD, tableName, tableComment);

        if (null != pageNo && null != pageSize){
            Map<String, Object> resultMap = new HashMap<>(2);
            resultMap.put("total", tableInfoList.size());
            int toIndex = (pageNo - 1) * pageSize + pageSize;
            if (toIndex > tableInfoList.size()){
                toIndex = tableInfoList.size();
            }
            resultMap.put("rows", tableInfoList.subList((pageNo - 1) * pageSize, toIndex));
            return resultMap;
        }else{
            return tableInfoList;
        }
    }

    @RequestMapping(value = "generate", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg generate(
            @RequestParam String tableName,
            @RequestParam String entityName,
            @ApiParam(value = "表id列类型", required = false, example = "Long") @RequestParam(required = false, defaultValue = "Long") String idType,
            @RequestParam(required = false) String genModules,
            Authentication authentication){
        if(null != authentication){
            Object principal = authentication.getPrincipal();
            UserEntity userEntity = new UserEntity();
            if(principal instanceof UserEntity){
                userEntity = (UserEntity)principal;
            }
            CodeGenerator.genCodeByCustomModelName(tableName, entityName, idType, genModules, userEntity.getUsername());
        }else{
            CodeGenerator.genCodeByCustomModelName(tableName, entityName, idType, genModules);
        }
        return ResultMsg.success();
    }

    @GetMapping(value = "codeGenerate")
    public String codeGenerate(
            Model model,
            @RequestParam String tableName,
            @RequestParam(required = false) String tableComment,
            @RequestParam String entityName){
        model.addAttribute("tableName", tableName);
        model.addAttribute("tableComment", tableComment);
        model.addAttribute("entityName", entityName);
        try {
            EntityDataModel entityDataModel = DbUtil.getEntityModel(dataSource.getConnection(), tableName, CodeGenerator.BASE_PACKAGE, entityName);
            List<Column> columns = entityDataModel.getColumns();

            //想隐藏显示的列
            List<String> hiddenColumns = new ArrayList<>();
            hiddenColumns.add("createDate");
            hiddenColumns.add("createBy");
            hiddenColumns.add("updateDate");
            hiddenColumns.add("updateBy");

            //想不在页面上输入的列
            List<String> denyInputColumns = new ArrayList<>();
            denyInputColumns.add("createDate");
            denyInputColumns.add("createBy");
            denyInputColumns.add("updateDate");
            denyInputColumns.add("updateBy");

            for(Column column : columns){
                if(hiddenColumns.contains(column.getName())){
                    column.setHidden(true);
                }
                if (denyInputColumns.contains(column.getName())){
                    column.setAllowInput(false);
                }
            }
            model.addAttribute("tableEntity", entityDataModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("easyuiComponents", dictTypeService.getTypeByGroupCode("easyui_component"));
        return "easyui/code_generate";
    }

    @RequestMapping(value = "genCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultMsg genCode(
            @RequestParam String tableName,
            @RequestParam String entityName,
            @RequestParam String tableComment,
            @RequestParam String idType,
            @RequestParam String genModules,
            @RequestParam String columns){
        List<Column> columnList = JSON.parseArray(columns, Column.class);
        EntityDataModel entityDataModel = new EntityDataModel();
        entityDataModel.setBasePackage(CodeGenerator.BASE_PACKAGE);
        entityDataModel.setEntityPackage(CodeGenerator.BASE_PACKAGE + ".entity");
        entityDataModel.setFileSuffix(".java");
        entityDataModel.setEntityName(entityName);
        entityDataModel.setTableName(tableName);
        entityDataModel.setTableComment(tableComment);
        entityDataModel.setColumns(columnList);
        entityDataModel.setModelNameUpperCamel(entityName);
        entityDataModel.setModelNameLowerCamel(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityDataModel.getEntityName()));
        CodeGenerator.genCodeByCustomModelName(tableName, entityName, idType, genModules, null, entityDataModel);
        return ResultMsg.success();
    }
}
