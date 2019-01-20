package com.simon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 错误页面
 *
 * @author simon
 * @date 2019-01-11
 **/
@ApiIgnore
@Slf4j
@Controller
public class ErrorPageController {
    @RequestMapping(value = "/error/{code}")
    public String error(@PathVariable int code, Model model){
        return "error/" + code;
    }
}
