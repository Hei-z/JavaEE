package com.atguigu.service;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;

import java.util.List;

public interface OrderService {
    /**
     * 对某个用户的购物车结算，保存订单信息
     */
    String checkout(Cart cart, User user);

    /**
     * 修改订单的状态
     */
    void update(String orderId, String status);

    /**
     * 获取所有的订单，管理员使用
     */
    List<Order> getOrders();

    /**
     * 获取某个用户的全部订单
     *
     * @param userId 用户id
     * @return 该用户的全部订单
     */
    List<Order> getOrdersByUserId(Integer userId);

}
