package com.simon.common.domain;

/**
 * 支付方式
 *
 * @author simon
 * @date 2018-11-23
 **/

public enum PaymentType {
    ALI_PAY(1, "alipay"),
    WX_PAY(2, "wxpay");

    private final int code;
    private final String msg;


    PaymentType(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
