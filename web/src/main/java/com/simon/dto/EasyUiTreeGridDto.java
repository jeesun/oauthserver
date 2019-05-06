package com.simon.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI TreeGrid
 *
 * @author simon
 * @date 2018-11-13
 **/
@ApiModel(description = "EasyUI TreeGrid节点")
@Data
@EqualsAndHashCode(callSuper = false)
public class EasyUiTreeGridDto implements Serializable {
    private static final long serialVersionUID = -6124138315161529007L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
    private Long id;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "菜单组id")
    private Long pid;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "默认状态[closed,open]")
    private String state;

    private List<EasyUiTreeGridDto> children;
}
