package com.simon.dto.ueditor;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 文件信息
 *
 * @author simon
 * @date 2018-12-13
 **/
@ApiModel(description = "文件信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class FileInfo implements Serializable {
    private static final long serialVersionUID = 8771384805158268668L;

    private Integer code;

    private String state;

    private String url;

    private String title;

    private String original;
}
