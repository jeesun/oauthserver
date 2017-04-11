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

    private static final Long timestamp = System.currentTimeMillis();

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public Map<String, Object> hello(){
        Map<String, Object> responseMap = new LinkedHashMap<>();
        try {
            responseMap.put("timestamp", timestamp);
            responseMap.put("HostAddress", InetAddress.getLocalHost().getHostAddress());
            responseMap.put("HostName", InetAddress.getLocalHost().getHostName());
            responseMap.put("CanonicalHostName", InetAddress.getLocalHost().getCanonicalHostName());
            responseMap.put("Address", InetAddress.getLocalHost().getAddress());
            responseMap.put("LocalHost", InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            responseMap.put("error", e.toString());
            responseMap.put("errorMsg", e.getMessage());
        }
        return responseMap;
    }
}
