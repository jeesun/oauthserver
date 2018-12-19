package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.service.AccountBindService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号绑定
 *
 * @author simon
 * @date 2018-12-04
 **/
@Slf4j
@Api(description = "账号绑定")
@Controller
@RequestMapping("/api/accountBinds")
public class AccountBindController extends BaseController {
    @Autowired
    private AccountBindService accountBindService;


}
