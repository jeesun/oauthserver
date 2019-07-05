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
    FAIL_ICON_CLASS_EXISTS(500001, "图标class已存在"),
    FAIL_PHONE_EXISTS(500002, "手机号已存在"),
    FAIL_EMAIL_EXISTS(500003, "邮箱已存在"),
    FAIL_PHONE_OR_EMAIL_EXISTS(500004, "手机号或邮箱已存在"),
    FAIL_INCORRECT_PASSWORD(500005, "密码错误"),
    FAIL_SEND_SMS(500006, "验证码发送失败"),
    ERROR_VERI_CODE(404001, "验证码错误"),
    ERROR_INVALID_PHONE(404002, "手机号格式不正确"),
    ERROR_FILE_NOT_FOUND(404003, "文件不存在");

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
