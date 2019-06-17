package com.simon.controller;

import com.alibaba.fastjson.JSON;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultCode;
import com.simon.common.domain.ResultMsg;
import com.simon.common.domain.UserEntity;
import com.simon.dto.FontAwesomeDto;
import com.simon.model.FontAwesome;
import com.simon.service.DictTypeService;
import com.simon.service.FontAwesomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Font Awesome字体
 *
 * @author simon
 * @date 2018-11-14
 **/
@Slf4j
@ApiIgnore
@Api(value = "Font Awesome字体")
@Controller
@RequestMapping("/api/fontAwesomes")
public class FontAwesomeController extends BaseController {
    @Autowired
    private FontAwesomeService fontAwesomeService;

    @Autowired
    private DictTypeService dictTypeService;

    @GetMapping("list")
    public String list(Model model) {
        return "vue/fontAwesome/list";
    }

    @GetMapping("add")
    public String add(Model model, Locale locale) {
        model.addAttribute("statusList", listToMap(dictTypeService.getTypeByGroupCode("status", locale.toString())));
        return "vue/fontAwesome/add";
    }

    @GetMapping("edit")
    public String edit(Model model, Locale locale, @RequestParam Integer id) {
        model.addAttribute("entity", entityToMap(fontAwesomeService.findById(id)));
        model.addAttribute("statusList", listToMap(dictTypeService.getTypeByGroupCode("status", locale.toString())));
        return "vue/fontAwesome/edit";
    }

    @GetMapping("data")
    @ResponseBody
    public EasyUIDataGridResult<FontAwesome> data(
            @ApiParam(value = "英文标签") @RequestParam(required = false) String labelEnUs,
            @ApiParam(value = "中文标签") @RequestParam(required = false) String labelZhCn,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true) @RequestParam Integer pageSize,
            @ApiParam(value = "排序") @RequestParam(required = false, defaultValue = "") String orderBy) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("labelEnUs", labelEnUs);
        params.put("labelZhCn", labelZhCn);
        return new EasyUIDataGridResult<>(fontAwesomeService.getList(params, pageNo, pageSize, orderBy));
    }

    @GetMapping("dtoData")
    @ResponseBody
    public ResultMsg<List<FontAwesomeDto>> dtoData(Locale locale) {
        return ResultMsg.success(fontAwesomeService.getDtos(locale.toString()));
    }

    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<ResultMsg> add(@RequestBody FontAwesome body, Authentication authentication) {
        //检查是否iconClass已存在
        if (fontAwesomeService.countByIconClass(body.getIconClass()) > 0) {
            return new ResponseEntity<>(ResultMsg.fail(ResultCode.FAIL_ICON_CLASS_EXISTS), HttpStatus.CONFLICT);
        }

        UserEntity userEntity = getCurrentUser(authentication);
        body.setCreateDate(LocalDateTime.now());
        body.setCreateBy(userEntity.getId());
        fontAwesomeService.insertSelective(body);
        return new ResponseEntity<>(ResultMsg.success(), HttpStatus.CREATED);
    }

    @PatchMapping("edit")
    @ResponseBody
    public ResponseEntity<ResultMsg> edit(@RequestBody FontAwesome body, Authentication authentication) {
        UserEntity userEntity = getCurrentUser(authentication);
        body.setUpdateDate(LocalDateTime.now());
        body.setUpdateBy(userEntity.getId());
        log.info(JSON.toJSONString(body));
        fontAwesomeService.updateByPrimaryKeySelective(body);
        return new ResponseEntity<>(ResultMsg.success(), HttpStatus.OK);
    }

    @DeleteMapping("ids/{ids}")
    @ResponseBody
    public ResultMsg delete(@PathVariable String ids) {
        fontAwesomeService.deleteByIds(ids);
        return ResultMsg.success();
    }
}