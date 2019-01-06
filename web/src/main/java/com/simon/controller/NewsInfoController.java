package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.model.NewsInfo;
import com.simon.service.NewsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

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

    @ApiIgnore
    @GetMapping(params = "easyui-list")
    public String getEasyUIList(){
        return "easyui/newsInfo/list";
        //return "easyui/news_info";
    }

    @ApiIgnore
    @ApiOperation(value = "easyui列表")
    @GetMapping("easyui/list")
    @ResponseBody
    public EasyUIDataGridResult<NewsInfo> getEasyUIList(
            @ApiParam(value = "新闻类型") @RequestParam(required = false) Integer newsType,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new LinkedHashMap<>();
        log.info("newsType=" + newsType);
        if(null != newsType){
            params.put("newsType", newsType);
        }
        return new EasyUIDataGridResult<>(newsInfoService.getList(params, pageNo, pageSize, orderBy));
    }

    @PostMapping
    @ResponseBody
    public ResultMsg add(@RequestBody NewsInfo body){
        newsInfoService.save(body);
        return ResultMsg.success();
    }

    @PatchMapping
    @ResponseBody
    public ResultMsg update(@RequestBody NewsInfo body){
        newsInfoService.updateByPrimaryKeySelective(body);
        return ResultMsg.success();
    }

    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids){
        newsInfoService.deleteByIds(ids);
        return ResultMsg.success();
    }

    @GetMapping("add")
    public String add(){
        return "easyui/newsInfo/add";
    }

    @GetMapping("edit")
    public String edit(@RequestParam Long id, Model model){
        model.addAttribute("newsInfo", newsInfoService.findById(id));
        return "easyui/newsInfo/edit";
    }
}
