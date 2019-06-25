package com.simon.dto;

import com.simon.common.code.Column;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author simon
 * @date 2019-05-03
 */
@ApiModel(description = "代码生成请求Dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class GenCodeDto implements Serializable {
    private static final long serialVersionUID = 2400754573770527147L;

    private String[] allowedRoles;

    private Long pid;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "实体类名")
    private String entityName;

    @ApiModelProperty(value = "表标注")
    private String tableComment;

    @ApiModelProperty(value = "id类型")
    private String idType;

    @ApiModelProperty(value = "要生成的模块代码")
    private String[] genModules;

    @ApiModelProperty(value = "列信息")
    private List<Column> columns;

    @ApiModelProperty(value = "模块目录")
    private String moduleDir;

    @ApiModelProperty(value = "基础包名", example = "com.simon")
    private String basePackage;

    @ApiModelProperty(value = "要生成的位置是main目录还是test目录")
    private String mainOrTest;
}
