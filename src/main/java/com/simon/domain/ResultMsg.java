package com.simon.domain;

public class ResultMsg {
    private Integer code;
    private String message;
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
