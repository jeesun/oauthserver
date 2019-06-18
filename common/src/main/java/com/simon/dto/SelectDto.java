package com.simon.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author simon
 * @version 1.0
 * @date 2019-06-17 18:06
 */
@ApiModel(description = "Vue选择框选项dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class SelectDto implements Serializable {
    private static final long serialVersionUID = 590332695500044576L;

    private String label;

    private String value;
}
