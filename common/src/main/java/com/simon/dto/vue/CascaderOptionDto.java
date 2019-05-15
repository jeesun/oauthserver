package com.simon.dto.vue;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author simon
 */
@ApiModel(description = "Element UI Cascader级联选择器item")
@Data
@EqualsAndHashCode(callSuper = false)
public class CascaderOptionDto implements Serializable {
    private static final long serialVersionUID = 9107749715461125671L;

    private Integer id;

    private String label;

    private String value;

    private CascaderOptionDto[] children;
}
