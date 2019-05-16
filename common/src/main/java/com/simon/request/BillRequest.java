package com.simon.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 订单请求
 *
 * @author simon
 * @date 2018-11-20
 **/
@ApiModel(description = "订单请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class BillRequest implements Serializable {

    private static final long serialVersionUID = 6180084518564838267L;

    @ApiModelProperty(value = "账单分类")
    private String billType;

    @ApiModelProperty(value = "付款金额", notes = "[{支付宝:支持浮点型，0.01代表1分钱}, {微信:支持整型，最小1代表1分钱}]")
    private Double totalAmount;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "账单描述(商品描述)")
    private String billDesc;

    @ApiModelProperty(value = "付款详情页的订单信息")
    private String billContext;

    @ApiModelProperty(value = "支付方式[{alipay:支付宝}, {wxpay:微信}]")
    private String paymentType;
}
