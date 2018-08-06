package com.simon.controller;

import com.simon.common.domain.ResultMsg;
import com.simon.model.NewsInfo;
import com.simon.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return ResultMsg.success(200, "", newsInfoService.findAll(pageNo));
    }

    @PostMapping("")
    public ResultMsg post(@RequestBody NewsInfo newsInfo){
        newsInfoService.save(newsInfo);
        return ResultMsg.success(200, "新增成功");
    }
}
