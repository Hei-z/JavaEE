package com.atguigu.service.impl;

import com.atguigu.bean.*;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderItemService;
import com.atguigu.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    private BookService bookService = new BookServiceImpl();

    /**
     * 对某个用户的购物车结算，保存订单信息
     *
     * @param cart 购物车信息，包括了所有的购物项
     * @param user 当前用户
     * @return 订单id
     */
    @Override
    public String checkout(Cart cart, User user) {
        // 生成订单id，采用当前时间戳加用户id
        String orderId = System.currentTimeMillis() + "" + user.getId();
        // 获取购物车中所有的购物项
        List<CartItem> cartItems = cart.getItems();
        // 生成订单项
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartItem.getCount());
            orderItem.setOrderId(orderId);
            orderItem.setPrice(cartItem.getBook().getPrice());
            orderItem.setTitle(cartItem.getBook().getTitle());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItems.add(orderItem);

            // 减库存、加销量，一定要从数据库中取出book，因为在并发情况下，可能有多个
            // 用户修改book销量和库存
            // 采用Book book = cartItem.getBook()是错的，并发下会出错
            // 比如两个用户同时将商品加入购物车，但此时，用户1结账了，那么此时库存和销量就变了
            // 而用户2购物车中的内容还是原来的，不是最新数据，用户2再结账，那么就有还是在原来的基础上
            // 改库存和销量
            // 详细看https://www.bilibili.com/video/BV1q4411u7mM?p=218
            Book book = bookService.getOne(cartItem.getBook());
            book.setStock(book.getStock() - cartItem.getCount());
            book.setSales(book.getSales() + cartItem.getCount());
            bookService.update(book);
        }
        // 创建订单对象，将订单保存到数据库
        // 要先保存订单对象，再保存所有订单项，因为有外键约束
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(0);
        order.setCreateDate(new Date());
        order.setTotalPrice(cart.getTotalPrice());
        order.setUserId(user.getId());
        orderDao.saveOrder(order);

        // 保存订单之后，未保存订单项之前出现异常
//        int i = 1 / 0;

        // 将所有的订单项存入数据库
        orderItemService.saveItems(orderItems);

        // 清空购物车
        cart.clear();
        return orderId;
    }

    /**
     * 修改订单的状态
     */
    @Override
    public void update(String orderId, String status) {
        Order order = new Order();
        order.setOrderId(orderId);
        int intStat = 0;
        try {
            intStat = Integer.parseInt(status);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        order.setStatus(intStat);
        orderDao.updateStatus(order);
    }

    /**
     * 获取所有的订单，管理员使用
     */
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    /**
     * 获取某个用户的全部订单
     *
     * @param userId 用户id
     * @return 该用户的全部订单
     */
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderDao.getOrdersByUserId(userId);
    }
}
