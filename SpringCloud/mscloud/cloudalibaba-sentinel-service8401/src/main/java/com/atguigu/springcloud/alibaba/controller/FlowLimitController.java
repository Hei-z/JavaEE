package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "--- testB");
        return "------testB";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
            log.info("testD 测试RT");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        log.info("异常数测试");
        int i = 10 / 0;
        return "------testD";
    }

    @GetMapping("/testHotKey")
    // value：给当前资源取一个名字，在sentinel中配置的时候是采用这个名字进行配置
    // blockHandler：指定兜底方法
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        log.info("热点key限流测试");
        return "------testHotKey";
    }

    // 兜底方法
    public String deal_testHotKey(String p1, String p2, BlockException blockException) {
        return "------deal_testHotKey";
    }

}

