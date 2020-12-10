package com.atguigu.test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.OrderItem;
import com.atguigu.bean.User;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderItemService;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.service.impl.OrderItemServiceImpl;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.List;

public class OrderTest {
    private OrderService orderService = new OrderServiceImpl();
    private BookService bookService = new BookServiceImpl();

    private OrderItemService orderItemService = new OrderItemServiceImpl();

    @Test
    public void test1() {
        Cart cart = new Cart();
        Book book = new Book();
        book.setId(1);
        cart.addItem2Cart(bookService.getOne(book));
        cart.addItem2Cart(bookService.getOne(book));
        book.setId(10);
        cart.addItem2Cart(bookService.getOne(book));
        User user = new User();
        user.setId(1);
        orderService.checkout(cart, user);
    }

    @Test
    public void test2() {
        String orderId = "16065660583071";
        orderService.update(orderId, "1");
    }

    @Test
    public void test3() {
        List<OrderItem> orderItems = orderItemService.getOrderItems("16065660583071");
        System.out.println(orderItems);
    }
}
