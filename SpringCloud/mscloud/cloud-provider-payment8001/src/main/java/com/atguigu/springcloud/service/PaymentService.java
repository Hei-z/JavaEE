package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * PaymentService 接口
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}
