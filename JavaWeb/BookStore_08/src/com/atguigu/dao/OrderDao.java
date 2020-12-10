package com.atguigu.dao;

import com.atguigu.bean.Order;

import java.util.List;

/**
 * 订单Dao
 */
public interface OrderDao {
    /**
     * 保存一个订单
     */
    int saveOrder(Order order);

    /**
     * 更改某个订单的状态
     */
    int updateStatus(Order order);

    /**
     * 获取所有的订单，给管理员使用
     */
    List<Order> getOrders();

    /**
     * 根据某个用户的id，查询其所有订单
     */
    List<Order> getOrdersByUserId(Integer userId);
}
