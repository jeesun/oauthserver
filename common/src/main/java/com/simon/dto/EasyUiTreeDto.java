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
 * EasyUI Tree节点
 *
 * @author simon
 * @date 2019-01-12
 **/
@ApiModel(description = "EasyUI Tree节点")
@Data
@EqualsAndHashCode(callSuper = false)
public class EasyUiTreeDto implements Serializable {
    private static final long serialVersionUID = -6956516610093262687L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long pid;

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    private String text;

    @ApiModelProperty(value = "权限")
    private String authority;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "关联id")
    private Long linkId;

    private Boolean checked = false;

    @ApiModelProperty(value = "默认状态[closed,open]")
    private String state = "open";

    private List<EasyUiTreeDto> children;

    @ApiModelProperty(value = "位置关系", example = "1-1-1")
    private String location;
}
