package com.simon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by simon on 2017/4/12.
 */
@RestController
@RequestMapping("/api/hellos")
public class HelloController {

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public Map<String, Object> hello(){
        Map<String, Object> responseMap = new LinkedHashMap<>();
        try {
            responseMap.put("ip", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return responseMap;
    }
}
