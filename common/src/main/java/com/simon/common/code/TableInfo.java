package com.simon.common.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 表信息
 *
 * @author simon
 * @date 2018-10-07
 **/

@ApiModel(description = "表信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class TableInfo {
    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "表标注")
    private String tableComment;

    @ApiModelProperty(value = "实体类名")
    private String entityName;
}
