package com.atguigu.springcloud.test;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDao {
    @Autowired
    private PaymentDao paymentDao;
    @Test
    void test() {
        Payment paymentById = paymentDao.getPaymentById(1L);
    }
}
