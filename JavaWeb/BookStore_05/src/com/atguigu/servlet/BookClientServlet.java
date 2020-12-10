package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理用户对图书的处理
 */
public class BookClientServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 查看分页图书数据
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取页码
        String pn = request.getParameter("pn");
        Page<Book> page = bookService.getPage(pn, "4");
        // 设置URL
        page.setUrl("client/BookClientServlet?method=page");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);
    }

    /**
     * 根据最小和最大价格范围分页查询数据
     */
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        String pn = request.getParameter("pn");
        Page<Book> page = bookService.getPageByPrice(pn, "4", min, max);
        // 设置url，并且把最小和最大价格也设置进去，在选择之后页码的时候可以按照最小和最大价格继续请求
        page.setUrl("client/BookClientServlet?method=pageByPrice&min=" + min + "&max=" + max);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);
    }
}
