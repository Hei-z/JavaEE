package com.atguigu.service.impl;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    /**
     * 根据订单id 查询该订单所有的订单项
     */
    @Override
    public List<OrderItem> getOrderItems(String ordId) {
        return orderItemDao.getOrderItems(ordId);
    }

    /**
     * 批量插入订单项
     *
     * @param items 订单项
     */
    @Override
    public void saveItems(List<OrderItem> items) {
        orderItemDao.saveBatch(items);
    }
}
