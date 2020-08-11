package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {

    @Value("${server.port}")
    private int port;

    @RequestMapping("/getPort")
    public  String getPort(){

        return "您当前访问的服务器端口号:"+port;
    }
}
