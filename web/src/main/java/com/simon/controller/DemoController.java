package com.simon.controller;

import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author simon
 * @date 2018-12-29
 **/

@Controller
@RequestMapping("/api/demos")
public class DemoController {
    @Autowired
    private ProvinceService provinceService;

    @PermitAll
    @GetMapping()
    @ResponseBody
    public EasyUIDataGridResult getData(int page, int rows){
        List<Map<String, Object>> mapList = new ArrayList<>();
        for(int i = 0; i < 28; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("itemid", "demo" + i);
            mapList.add(map);
        }
        if(page* rows < 28){
            List<Map<String, Object>> subList = mapList.subList((page-1)*rows, page* rows);
            return new EasyUIDataGridResult(28L, subList);
        }else{
            List<Map<String, Object>> subList = mapList.subList((page-1)*rows, 27);
            return new EasyUIDataGridResult(28L, subList);
        }
    }

    @PermitAll
    @GetMapping("province")
    @ResponseBody
    public ResultMsg getProvince(){
        return ResultMsg.success(provinceService.findAll());
    }

}
