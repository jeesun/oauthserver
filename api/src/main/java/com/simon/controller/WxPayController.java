package com.simon.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.common.plugins.wxpay.WxPayProperties;
import com.simon.model.Bill;
import com.simon.request.BillRequest;
import com.simon.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 微信支付
 *
 * @author simon
 * @date 2018-11-20
 **/
@Slf4j
@Api(description = "微信支付")
@RestController
@RequestMapping("/api/wxPays")
public class WxPayController extends BaseController {
    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private BillService billService;

    @PermitAll
    @ApiOperation(value = "测试统一下单")
    @PostMapping("/testCreateOrder")
    public ResultMsg testCreateOrder(HttpServletRequest request) throws WxPayException {
        BillRequest billRequest = new BillRequest();
        billRequest.setTotalAmount(0.01);
        billRequest.setQuantity(1);
        billRequest.setBillType("0");
        billRequest.setBillContext("付款详情页的订单信息");
        billRequest.setBillDesc("测试统一下单");
        return createOrder(request, billRequest);
    }

    @PermitAll
    @ApiOperation(value = "统一下单，并组装所需支付参数")
    @PostMapping("/createOrder")
    public ResultMsg createOrder(HttpServletRequest request, @RequestBody BillRequest billRequest) throws WxPayException {
        Bill bill = billService.createBill(billRequest);
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody(bill.getBillContext());
        orderRequest.setMchId(wxPayProperties.getMchId());
        orderRequest.setAppid(wxPayProperties.getAppId());
        orderRequest.setOutTradeNo(bill.getOutTradeNo());
        //支付金额单位是分，所以bill.getTotalAmount()要乘以100
        orderRequest.setTotalFee(new Double(bill.getTotalAmount() * 100).intValue());
        orderRequest.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
        orderRequest.setNotifyUrl(wxPayProperties.getNotifyUrl());
        orderRequest.setTradeType("APP");
        orderRequest.setSpbillCreateIp(request.getRemoteAddr());
        return ResultMsg.success(this.wxPayService.createOrder(orderRequest));
    }

    @ApiOperation(value = "支付回调通知处理")
    @PostMapping("/notify/order")
    public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
        final WxPayOrderNotifyResult notifyResult = this.wxPayService.parseOrderNotifyResult(xmlData);
        billService.billPayed(notifyResult.getOutTradeNo());
        return WxPayNotifyResponse.success("成功");
    }
}
