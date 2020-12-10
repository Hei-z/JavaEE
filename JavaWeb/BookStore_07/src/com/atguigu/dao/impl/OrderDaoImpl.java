package com.atguigu.dao.impl;

import com.atguigu.bean.Order;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderDao;

import java.util.List;

/**
 * 订单Dao 实现类
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    /**
     * 保存订单到数据库
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into bs_order(order_id, create_date, total_price, status, user_id) values(?, ?, ?, ?, ?)";
        return update(sql, order.getOrderId(), order.getCreateDate(), order.getTotalPrice(), order.getStatus(), order.getUserId());
    }

    /**
     * 更新订单的状态
     */
    @Override
    public int updateStatus(Order order) {
        String sql = "update bs_order set status=? where order_id=?";
        return update(sql, order.getStatus(), order.getOrderId());
    }

    /**
     * 查询所有的订单，管理员使用
     */
    @Override
    public List<Order> getOrders() {
        String sql = "select order_id orderId, create_date createDate, total_price totalPrice, status, user_id userId from bs_order";
        return getBeanList(sql);
    }

    /**
     * 根据用户id 查询该用户所有的订单，用户使用
     */
    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        String sql = "select order_id orderId, create_date createDate, total_price totalPrice, status, user_id userId " +
                "from bs_order where user_id = ?";
        return getBeanList(sql, userId);
    }
}
