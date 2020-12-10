package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.util.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理购物车的Servlet
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 将购物项添加到购物车
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 这里的book 只有id，因为页面只传过来了id
        Book book = WebUtils.param2bean(request, new Book());
        // 查询出book 的详细信息
        book = bookService.getOne(book);
        HttpSession session = request.getSession();
        Cart cart = WebUtils.getCart(request);
        session.setAttribute("title", book.getTitle());
        cart.addItem2Cart(book);
        // 获取发起该请求的地址
        String referer = request.getHeader("referer");
        // 跳回到原来的页面
        response.sendRedirect(referer);
    }

    /**
     * 通过ajax将购物项添加到购物车
     */
    protected void addAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 这里的book 只有id，因为页面只传过来了id
        Book book = WebUtils.param2bean(request, new Book());
        // 查询出book 的详细信息
        book = bookService.getOne(book);
        HttpSession session = request.getSession();
        Cart cart = WebUtils.getCart(request);
        cart.addItem2Cart(book);
        int totalCount = cart.getTotalCount(); // 购物车中的总数量
        String title = book.getTitle(); // 书名
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("title", title);
        Gson gson = new Gson();
        // 将map转成json字符串，然后返回给前端
        String jsonStr = gson.toJson(map);
        response.getWriter().write(jsonStr);
    }

    /**
     * 删除购物车中购物项
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Cart cart = WebUtils.getCart(request);
        cart.deleteItem(id);
        // 获取发起该请求的地址
        String referer = request.getHeader("referer");
        // 跳回到原来的页面
        response.sendRedirect(referer);
    }

    /**
     * 处理ajax发送修改购物车中商品数量的请求
     */
    protected void updateAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = WebUtils.getCart(request);
        String id = request.getParameter("id");
        String count = request.getParameter("count");
        cart.updateCount(id, count);
        // 根据id 查询该购物项
        CartItem item = cart.getItem(id);
        // 获取该购物项的总金额
        double itemPrice = item.getTotalPrice();
        // 获取购物车中商品的总量
        int totalCount = cart.getTotalCount();
        // 获取购物车中商品的总金额
        double totalPrice = cart.getTotalPrice();
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("itemPrice", itemPrice);
        map.put("totalPrice", totalPrice);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        response.getWriter().write(s);
    }


    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = WebUtils.getCart(request);
        String id = request.getParameter("id");
        String count = request.getParameter("count");
        cart.updateCount(id, count);
        // 获取发起该请求的地址
        String referer = request.getHeader("referer");
        // 跳回到原来的页面
        response.sendRedirect(referer);
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = WebUtils.getCart(request);
        cart.clear();
        // 获取发起该请求的地址
        String referer = request.getHeader("referer");
        // 跳回到原来的页面
        response.sendRedirect(referer);
    }
}
