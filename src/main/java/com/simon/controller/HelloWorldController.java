package com.simon.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * 测试
 *
 * @author simon
 * @create 2018-05-31 10:10
 **/

@RestController
@RequestMapping("/api/helloWorlds")
public class HelloWorldController {

    @RolesAllowed("ADMIN")
    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public void test(@RequestParam String access_token){

    }
}
