package com.simon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 左侧菜单栏节点
 *
 * @author simon
 * @date 2018-11-08
 **/

@ApiModel(description = "左侧菜单栏节点")
@Data
@EqualsAndHashCode(callSuper = false)
public class Tree<T> {
    @ApiModelProperty(value = "节点id")
    private String id;

    @ApiModelProperty(value = "显示节点文本")
    private String text;

    @ApiModelProperty(value = "节点状态[open, closed]")
    private Map<String, Object> state;

    @ApiModelProperty(value = "节点是否被选中[true, false]")
    private boolean checked = false;

    @ApiModelProperty(value = "节点属性")
    private Map<String, Object> attributes;

    @ApiModelProperty(value = "节点的子节点")
    private List<Tree<T>> children = new ArrayList<>();

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "是否有父节点")
    private boolean hasParent = false;

    @ApiModelProperty(value = "是否有子节点")
    private boolean hasChildren = false;
}
