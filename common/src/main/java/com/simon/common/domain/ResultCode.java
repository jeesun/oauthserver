package com.simon.common.domain;

/**
 * 结果码
 *
 * @author simon
 * @date 2018-10-31
 **/

public enum ResultCode {
    /**
     * 结果码
     */
    SUCCESS(200, "操作成功"),
    FAIL(500, "服务器内部错误"),
    ERROR_VERI_CODE(404001, "验证码错误"),
    ERROR_INVALID_PHONE(404002, "手机号格式不正确");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(int code){
        for(ResultCode resultCode : ResultCode.values()){
            if (resultCode.getCode() == code){
                return resultCode.getMsg();
            }
        }
        return null;
    }

    public static boolean codeExists(int code){
        for(ResultCode resultCode : ResultCode.values()){
            if (resultCode.getCode() == code){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
