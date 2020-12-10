package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
