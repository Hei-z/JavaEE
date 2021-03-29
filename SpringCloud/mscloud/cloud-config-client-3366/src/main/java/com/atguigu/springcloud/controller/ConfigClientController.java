package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    // 从系统属性中获取config.env的值，在github上有config.env配置项
    // 然后Config Server端会从github上获取该配置项的值
    // 然后这里的Config Client端会从Config Server端获取该配置项，
    // 存放到系统属性中
    @Value("${config.env}")
    private String configInfo;

    @GetMapping("/configEnv")
    public String getConfigInfo() {
        return "server port: " + serverPort + "\t config info: " + configInfo;
    }

}
