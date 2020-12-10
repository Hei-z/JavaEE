package com.atguigu.servlet;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.bean.User;
import com.atguigu.constants.Constants;
import com.atguigu.service.OrderItemService;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderItemServiceImpl;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理用户对订单的请求
 */
public class OrderClientServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();

    /**
     * 结账，将购物车中的购物项生成订单，并保存
     */
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户
        User loginUser = WebUtils.getLoginUser(request);
        // 判断用户是否登录
        if (loginUser != null) {
            // 获取购物车
            Cart cart = WebUtils.getCart(request);
            if (cart != null) {
                String orderId = orderService.checkout(cart, loginUser);
                request.setAttribute("orderId", orderId);
                request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "请先登录，再进行购买");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    /**
     * 显示某个用户所有的订单
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = WebUtils.getLoginUser(request);
        if (loginUser != null) {
            List<Order> orders = orderService.getOrdersByUserId(loginUser.getId());
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "请先登录，再查看订单");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    /**
     * 查看订单详情
     */
    protected void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取订单的id
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderItemService.getOrderItems(orderId);
        request.setAttribute("orderItems", orderItems);
        request.getRequestDispatcher("/pages/order/orderItems.jsp").forward(request, response);
    }

    /**
     * 用户确认收货
     */
    protected void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        orderService.update(orderId, Constants.RECEIVED); // Constants.RECEIVED == "2"
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }
}
