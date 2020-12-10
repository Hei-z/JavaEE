package com.atguigu.servlet;

import com.atguigu.bean.Order;
import com.atguigu.constants.Constants;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理管理员的订单请求
 */
public class OrderManagerServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 管理员查出所有的订单
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.getOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     * 管理员发货
     */
    protected void deliver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        // 更改订单状态为已发货
        orderService.update(orderId, Constants.DELIVERED); // Constants.DELIVERED == "1"
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }
}
