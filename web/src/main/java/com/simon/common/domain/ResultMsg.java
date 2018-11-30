package com.simon.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 标准返回格式
 * @author simon
 * @date 2018-09-18
 */
@ApiModel(description = "标准返回格式")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResultMsg<T> implements Serializable {
    private static final long serialVersionUID = -452209559974344268L;

    @ApiModelProperty(value = "返回码", required = true)
    private Integer code;

    @ApiModelProperty(value = "返回信息", required = true)
    private String message;

    @ApiModelProperty(value = "返回数据", required = true)
    private T data;

    public ResultMsg() {
    }

    public ResultMsg(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ResultMsg(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultMsg success(){
        return resultCode(ResultCode.SUCCESS);
    }

    public static ResultMsg success(Integer code, String message){
        return new ResultMsg<>(code, message);
    }

    public static <T> ResultMsg<T> success(T data){
        return resultCode(ResultCode.SUCCESS, data);
    }

    public static <T> ResultMsg<T> success(Integer code, String message, T data){
        return new ResultMsg<>(code, message, data);
    }

    public static <T> ResultMsg<T> fail(Integer code, String message, T data){
        return new ResultMsg<>(code, message, data);
    }

    public static <T> ResultMsg<T> fail(Integer code, String message){
        return new ResultMsg<>(code, message, null);
    }

    public static ResultMsg resultCode(ResultCode resultCode){
        return new ResultMsg(resultCode.getCode(), resultCode.getMsg());
    }

    public static <T> ResultMsg<T> resultCode(ResultCode resultCode, T data){
        return new ResultMsg<>(resultCode.getCode(), resultCode.getMsg(), data);
    }
}
