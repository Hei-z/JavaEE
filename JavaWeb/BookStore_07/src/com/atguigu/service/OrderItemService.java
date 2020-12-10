package com.atguigu.service;

import com.atguigu.bean.OrderItem;

import java.util.List;

public interface OrderItemService {
    /**
     * 根据订单id查询出所有的订单项
     */
    List<OrderItem> getOrderItems(String ordId);

    /**
     * 保存某个订单项到数据库
     */
    void saveItems(List<OrderItem> items);


}
