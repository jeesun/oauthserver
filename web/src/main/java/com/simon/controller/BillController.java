package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.model.Bill;
import com.simon.service.BillService;
import com.simon.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 订单
 *
 * @author simon
 * @date 2018-11-20
 **/
@Slf4j
@Api(description = "订单")
@Controller
@RequestMapping("/api/bills")
public class BillController extends BaseController {
    @Autowired
    private BillService billService;

    @Autowired
    private DictTypeService dictTypeService;

    @ApiIgnore
    @GetMapping(params = "easyui-list")
    public String getEasyUIList(Model model){
        model.addAttribute("billType", dictTypeService.getTypeByGroupCode("bill_type"));
        model.addAttribute("billStatus", dictTypeService.getTypeByGroupCode("bill_status"));
        model.addAttribute("paymentType", dictTypeService.getTypeByGroupCode("payment_type"));
        return "easyui/bill";
    }

    @ApiIgnore
    @ApiOperation(value = "easyui列表")
    @GetMapping("easyui/list")
    @ResponseBody
    public EasyUIDataGridResult<Bill> getEasyUIList(
            @ApiParam(value = "账单分类") @RequestParam(required = false) String billType,
            @ApiParam(value = "账单状态") @RequestParam(required = false) String billStatus,
            @ApiParam(value = "用户id") @RequestParam(required = false) Long userId,
            @ApiParam(value = "用户名(昵称)") @RequestParam(required = false) String username,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("billType", billType);
        params.put("billStatus", billStatus);
        params.put("userId", userId);
        params.put("username", username);
        return new EasyUIDataGridResult<>(billService.getDtoList(params, pageNo, pageSize, orderBy));
    }
}