package com.simon.dto;

import com.simon.common.code.Column;
import io.swagger.annotations.ApiModel;
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

    private String tableName;

    private String entityName;

    private String tableComment;

    private String idType;

    private String[] genModules;

    private List<Column> columns;
}
