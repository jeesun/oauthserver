package com.simon.common.code;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模板生成属性
 * @author simon
 * @date 2018-08-07
 */
@Data
public class EntityDataModel implements Serializable {
    private static final long serialVersionUID = 32546422336594242L;

    /**
     * 实体类名首字母大写
     */
    private String modelNameLowerCamel;

    /**
     * 实体类名小写下划线
     */
    private String modelNameUpperCamel;

    /**
     * 基础包名，在pom.xml中配置
     */
    private String basePackage;

    /**
     * 实体类包名
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
     * 表标注
     */
    private String tableComment;

    /**
     * 字段集合
     */
    private List<Column> columns;

}