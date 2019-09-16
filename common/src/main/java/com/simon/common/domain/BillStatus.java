package com.simon.common.domain;

/**
 * 订单状态
 *
 * @author simon
 * @date 2018-11-23
 **/

public enum BillStatus {
    /**
     * 订单状态
     */
    WAIT_PAY(1, "待付款"),
    PAYED(2, "已付款，待发货"),
    DELIVERED(3, "已发货，待收货"),
    SUCCESS(4, "交易成功"),
    CLOSED(5, "交易关闭"),
    REFUNDING(6, "退款中");


    private final int code;
    private final String msg;


    BillStatus(int code, String msg){
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
        return "BillStatus{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
