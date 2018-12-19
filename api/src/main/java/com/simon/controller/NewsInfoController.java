package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.model.NewsInfo;
import com.simon.service.NewsInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 新闻
 *
 * @author simon
 * @date 2018-11-15
 */
@Slf4j
@Api(description = "新闻")
@Controller
@RequestMapping("/api/newsInfos")
public class NewsInfoController extends BaseController {
    @Autowired
    private NewsInfoService newsInfoService;

    @PostMapping
    @ResponseBody
    public ResultMsg add(@RequestBody NewsInfo body){
        newsInfoService.save(body);
        return ResultMsg.success();
    }

    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids){
        newsInfoService.deleteByIds(ids);
        return ResultMsg.success();
    }
}
