package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// value = "CLOUD-PAYMENT-SERVICE: 指定要调用的服务
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    // /payment/get/{id}: 会给CLOUD-PAYMENT-SERVICE服务发送/payment/get/{id}请求
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 测试超时
     *
     * @return 无意义
     */
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}

