package com.simon.controller;

import com.simon.common.code.CodeGenerator;
import com.simon.common.code.TableInfo;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.common.utils.DbUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据表
 *
 * @author simon
 * @date 2018-10-06
 **/
@Api(description = "数据表")
@Slf4j
@Controller
@RequestMapping("/tables")
public class TableController extends BaseController {

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
            @ApiParam(value = "页码") @RequestParam(required = false) Integer page,
            @ApiParam(value = "每页条数")@RequestParam(required = false) Integer rows) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        /*String url = "jdbc:mysql://127.0.0.1:3306/thymelte?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false";
        String user = "root";
        String pwd = "19941017";*/
        /*String url = "jdbc:mysql://47.105.43.147:3306/top_dev?characterEncoding=utf8&useSSL=true";
        String user = "root";
        String pwd = "top@123456";*/
        List<TableInfo> tableInfoList = DbUtil.getTables(CodeGenerator.JDBC_DIVER_CLASS_NAME, CodeGenerator.JDBC_URL, CodeGenerator.JDBC_USERNAME, CodeGenerator.JDBC_PASSWORD, tableName, tableComment);

        if (null != page && null != rows){
            Map<String, Object> resultMap = new HashMap<>(2);
            resultMap.put("total", tableInfoList.size());
            int toIndex = (page - 1) * rows + rows;
            if (toIndex > tableInfoList.size()){
                toIndex = tableInfoList.size();
            }
            resultMap.put("rows", tableInfoList.subList((page - 1) * rows, toIndex));
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
            @RequestParam(required = false) String genModules){
        CodeGenerator.genCodeByCustomModelName(tableName, entityName, genModules);
        return ResultMsg.success();
    }
}
