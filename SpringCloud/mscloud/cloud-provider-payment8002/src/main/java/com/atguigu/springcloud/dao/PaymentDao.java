package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * PaymentDao 接口
 */
@Mapper
public interface PaymentDao {
    // 插入数据
    int create(Payment payment);

    // 查询数据
    Payment getPaymentById(Long id);
}
