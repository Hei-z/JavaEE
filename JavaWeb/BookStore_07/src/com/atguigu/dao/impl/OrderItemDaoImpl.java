package com.atguigu.dao.impl;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;

import java.util.List;

/**
 * 订单项Dao类实现
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    /**
     * 根据订单id 查询该订单所有的订单项
     */
    @Override
    public List<OrderItem> getOrderItems(String orderId) {
        String sql = "select  id, title, price, count, total_price totalPrice from bs_order_item where order_id=?";
        return getBeanList(sql, orderId);
    }

    /**
     * 保存一个订单项到数据库
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into bs_order_item(title, count, price, total_price, order_id) values(?,?,?,?,?)";
        return update(sql, orderItem.getTitle(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    /**
     * 批量插入订单
     *
     * @param items 插入的订单
     * @return 每条sql语句影响的行数
     */
    public int[] saveBatch(List<OrderItem> items) {
        String sql = "insert into bs_order_item(title, count, price, total_price, order_id) values(?,?,?,?,?)";
        Object[][] params = new Object[items.size()][];
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            params[i] = new Object[]{item.getTitle(), item.getCount(), item.getPrice(), item.getTotalPrice(), item.getOrderId()};
        }
        return batch(sql, params);
    }
}
