package com.simon.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 性别Dto
 *
 * @author simon
 * @date 2018-11-19
 **/
@ApiModel(description = "权限dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class StatisticDto {
    @ApiModelProperty(value = "分类")
    private String classify;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "统计")
    private Long value;

    @ApiModelProperty(value = "分类名称")
    private String name;
}
