package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理管理员对图书的处理
 */
public class BookManagerServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 请求分页数据
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取页码
        String pn = request.getParameter("pn");
        Page<Book> page = bookService.getPage(pn, "4");
        // 设置URL
        page.setUrl("manager/BookManagerServlet?method=page");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    /**
     * 查询所有图书信息并跳转到图书显示页面
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAll();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    /**
     * 添加图书，添加完成之后并跳转到图书列表页面
     * 添加图书采用update方法，该方法没用了
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.param2bean(request, new Book());
        bookService.add(book);
        // 重新请求图书显示页面
        response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=list");
    }

    /**
     * 删除图书
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.param2bean(request, new Book());
        bookService.delete(book);
        // 重新请求图书显示页面
        response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=list");
    }

    /**
     * 更新图书/添加图书
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pn = request.getParameter("pn");
        Book book = WebUtils.param2bean(request, new Book());
        // 如果是修改图书那么id > 0，否则id == 0 添加图书
        // 添加图书
        if (book.getId() == 0) {
            bookService.add(book);
        } else {
            // 修改图书
            bookService.update(book);
        }
        response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page&pn=" + pn);
    }

    /**
     * 查询一本图书
     */
    protected void getOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pn = request.getParameter("pn");
        Book book = WebUtils.param2bean(request, new Book());
        Book one = bookService.getOne(book);
        request.setAttribute("book", one);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp?pn=" + pn).forward(request, response);
    }
}
