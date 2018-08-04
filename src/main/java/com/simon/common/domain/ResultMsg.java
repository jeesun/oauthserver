package com.simon.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ResultMsg implements Serializable {
    private static final long serialVersionUID = -452209559974344268L;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    public ResultMsg() {
    }

    public ResultMsg(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ResultMsg(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultMsg success(Integer code, String message){
        return new ResultMsg(code, message);
    }

    public static ResultMsg success(Integer code, String message, Object data){
        return new ResultMsg(code, message, data);
    }


    public static ResultMsg fail(Integer code, String message, Object data){
        return new ResultMsg(code, message, data);
    }

    public static ResultMsg fail(Integer code, String message){
        return new ResultMsg(code, message, null);
    }
}
