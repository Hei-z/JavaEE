package com.atguigu.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    // 要使用static
    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(2020, "自定义的限流处理信息....CustomerBlockHandler", null);
    }

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(2020, "自定义的限流处理信息....CustomerBlockHandler2", null);
    }
}
