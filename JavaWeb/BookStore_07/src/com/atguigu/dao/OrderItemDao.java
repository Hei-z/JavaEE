package com.atguigu.dao;

import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;

import java.util.List;

/**
 * 订单项Dao
 */
public interface OrderItemDao {
    /**
     * 根据某个订单的id查询所有的订单项
     */
    List<OrderItem> getOrderItems(String orderId);

    /**
     * 保存某个订单项到数据库
     */
    int saveOrderItem(OrderItem orderItem);

    /**
     * 批量插入订单
     *
     * @param items 插入的订单
     * @return 每条sql语句影响的行数
     */
    int[] saveBatch(List<OrderItem> items);
}
