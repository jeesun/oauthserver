package com.simon.common.code;

/**
 * @author simon
 * @create 2018-08-07 21:14
 **/

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模板生成属性
 * Created by wangqichang on 2018/5/30.
 */
@Data
public class EntityDataModel implements Serializable {
    private static final long serialVersionUID = 32546422336594242L;
    /**
     * base package
     */
    private String entityPackage;
    /**
     * 文件名后缀
     */
    private String fileSuffix = ".java";

    /**
     * 实体名
     */
    private String entityName;

    /**
     * 作者 默认
     */
    private String AUTHOR=CodeGenerator.AUTHOR;

    /**
     * 创建时间
     */
    private String CREATE = CodeGenerator.CREATE;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段集合
     */
    private List<Column> columns;

}