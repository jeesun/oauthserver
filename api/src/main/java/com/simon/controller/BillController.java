package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.service.BillService;
import com.simon.service.DictTypeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}