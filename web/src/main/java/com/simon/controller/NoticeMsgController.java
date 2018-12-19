package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.service.NoticeMsgService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通知消息
 *
 * @author simon
 * @date 2018-11-24
 **/
@Slf4j
@Api(description = "通知消息")
@Controller
@RequestMapping("/api/noticeMsgs")
public class NoticeMsgController extends BaseController {
    @Autowired
    private NoticeMsgService noticeMsgService;


}
