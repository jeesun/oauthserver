package com.simon.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author simon
 * @date 2019-04-30
 */
@ApiModel(description = "侧边栏菜单dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class SideMenuDto implements Serializable {
    private static final long serialVersionUID = -7060081775049307332L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String name;
}
