package com.simon.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.common.plugins.alipay.AliPayConfig;
import com.simon.model.Bill;
import com.simon.request.BillRequest;
import com.simon.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝支付
 *
 * @author simon
 * @date 2018-11-20
 **/
@Slf4j
@Api(description = "支付宝支付")
@Controller
@RequestMapping("/api/aliPays")
public class AliPayController extends BaseController {

    @Autowired
    private AliPayConfig aliPayConfig;

    @Autowired
    private BillService billService;

    @PermitAll
    @RequestMapping(value = "testCreateAppOrder", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg testCreateAppOrder(){
        BillRequest billRequest = new BillRequest();
        billRequest.setBillDesc("app下单测试");
        billRequest.setBillContext("付款详情页的订单信息");
        billRequest.setBillType("1");
        billRequest.setQuantity(1);
        billRequest.setTotalAmount(0.01);
        return createOrder(billRequest);
    }

    @PermitAll
    @RequestMapping(value = "testCreateWebOrder", method = RequestMethod.GET)
    public void testCreateWebOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BillRequest billRequest = new BillRequest();
        billRequest.setBillDesc("web下单测试");
        billRequest.setBillContext("付款详情页的订单信息");
        billRequest.setBillType("1");
        billRequest.setQuantity(1);
        billRequest.setTotalAmount(0.01);
        createWebOrder(request, response, billRequest);
    }

    @ApiOperation(value = "下单（app原生）")
    @RequestMapping(value = "createAppOrder", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultMsg createOrder(@RequestBody BillRequest billRequest){
        Bill bill = billService.createBill(billRequest);
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = bill.getOutTradeNo();
        // 订单名称，必填
        String subject = bill.getBillContext();
        // 付款金额，必填
        String total_amount = bill.getTotalAmount().toString();
        // 商品描述，可空
        String body = bill.getBillDesc();
        // 超时时间 可空
        String timeout_express = "30m";
        // 销售产品码 必填
        String product_code = "QUICK_MSECURITY_PAY";

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.URL, aliPayConfig.APPID, aliPayConfig.RSA_PRIVATE_KEY, aliPayConfig.FORMAT, aliPayConfig.CHARSET, aliPayConfig.ALIPAY_PUBLIC_KEY, aliPayConfig.SIGNTYPE);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(out_trade_no);
        model.setTimeoutExpress(timeout_express);
        model.setTotalAmount(total_amount);
        model.setProductCode(product_code);
        request.setBizModel(model);
        request.setNotifyUrl(aliPayConfig.notify_url);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            //System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return ResultMsg.success(response.getBody());
        } catch (
                AlipayApiException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ResultMsg.resultCode(ResultCode.FAIL);
    }

    @PermitAll
    @ApiOperation(value = "下单（web）")
    @RequestMapping(value = "createWebOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public void createWebOrder(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody BillRequest billRequest) throws IOException {
        Bill bill = billService.createBill(billRequest);
        log.info(JSON.toJSONString(bill));
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = bill.getOutTradeNo();
        // 订单名称，必填
        String subject = bill.getBillContext();
        // 付款金额，必填
        String total_amount = bill.getTotalAmount().toString();
        // 商品描述，可空
        String body = bill.getBillDesc();
        // 超时时间 可空
        String timeout_express = "2m";
        // 销售产品码 必填
        String product_code = "QUICK_WAP_WAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(aliPayConfig.URL, aliPayConfig.APPID, aliPayConfig.RSA_PRIVATE_KEY, aliPayConfig.FORMAT, aliPayConfig.CHARSET, aliPayConfig.ALIPAY_PUBLIC_KEY,aliPayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest aliPayRequest=new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        aliPayRequest.setBizModel(model);
        // 设置异步通知地址
        aliPayRequest.setNotifyUrl(aliPayConfig.notify_url);
        // 设置同步地址
        aliPayRequest.setReturnUrl(aliPayConfig.return_url);

        // form表单生产
        String form = "";

        try {
            // 调用SDK生成表单
            form = client.pageExecute(aliPayRequest).getBody();
            response.setContentType("text/html;charset=" + aliPayConfig.CHARSET);
            //直接将完整的表单html输出到页面
            response.getWriter().write(form);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error(e.getErrCode());
            log.error(e.getErrMsg());
        }
    }

    @ApiIgnore
    @PermitAll
    @RequestMapping(value = "notifyUrl", method = RequestMethod.POST)
    @ResponseBody
    public String notifyUrl(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        log.info("执行支付宝回调notifyUrl");
        response.setContentType("text/html;charset=" + aliPayConfig.CHARSET);
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        log.info("--------------------------------------------------------------");
        log.error("notifyUrl params=" + params.toString());
        log.info("--------------------------------------------------------------");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

        //实收金额，单位为元，两位小数。该金额为本笔交易，商户账户能够实际收到的金额
        String receiptAmount = new String(request.getParameter("receipt_amount").getBytes("ISO-8859-1"),"UTF-8");

        String totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayConfig.ALIPAY_PUBLIC_KEY, aliPayConfig.CHARSET, aliPayConfig.SIGNTYPE);

        log.info("notifyUrl:verify_result=" + verify_result);

        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
            /*if(Float.parseFloat(receiptAmount) < Float.parseFloat(totalAmount)){//如果实付金额小于订单金额
                log.error("实付金额小于订单金额!");
                return "fail";
            }*/

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                billService.billPayed(out_trade_no);

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                billService.billPayed(out_trade_no);
                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            }else{
                log.error("支付失败，请稍后重试");
                billService.billPayFailed(out_trade_no);
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            return "success";	//请不要修改或删除
            //////////////////////////////////////////////////////////////////////////////////////////
        }else{
            billService.billPayFailed(out_trade_no);
            //验证失败
            return "fail";
        }
    }

    @ApiIgnore
    @PermitAll
    @RequestMapping(value = "returnUrl", method = RequestMethod.GET)
    @ResponseBody
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException{
        response.setContentType("text/html;charset=" + aliPayConfig.CHARSET);

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        log.info("--------------------------------------------------------------");
        log.error("returnUrl params=" + params.toString());
        log.info("--------------------------------------------------------------");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayConfig.ALIPAY_PUBLIC_KEY, aliPayConfig.CHARSET, aliPayConfig.SIGNTYPE);

        log.info("returnUrl:verify_result=" + verify_result);

        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
            return "验证成功<br />";
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            //////////////////////////////////////////////////////////////////////////////////////////
        }else{
            //该页面可做页面美工编辑
            return "验证失败";
        }
    }
}
