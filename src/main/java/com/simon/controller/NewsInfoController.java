package com.simon.controller;

import com.simon.domain.ResultMsg;
import com.simon.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新闻
 *
 * @author simon
 * @create 2018-07-24 0:30
 **/
@RestController
@RequestMapping("/newsInfos")
public class NewsInfoController {
    @Autowired
    private NewsInfoService newsInfoService;

    @GetMapping("")
    public ResultMsg getAll(@RequestParam int pageNo){
        return ResultMsg.success(200, "", newsInfoService.getAll(pageNo));
    }
}
