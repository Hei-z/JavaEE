package com.atguigu.service.impl;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public Book getOne(Book book) {
        return bookDao.getBook(book);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAllBook();
    }

    @Override
    public boolean add(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public boolean delete(Book book) {
        return bookDao.delBook(book);
    }

    @Override
    public boolean update(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Page<Book> getPage(String pageNo, String pageSize) {
        // 页码
        int pn = 1;
        // 每页的记录数
        int ps = 4;
        try {
            pn = Integer.parseInt(pageNo);
            ps = Integer.parseInt(pageSize);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        Page<Book> page = new Page<>();
        // 查询设置总记录数
        page.setTotalCount(bookDao.getTotalCount());
        // 设置每页大小
        page.setPageSize(ps);
        // 设置当前页面
        page.setPageNo(pn);

        // 查询分页数据
        List<Book> pageList = bookDao.getPageList(page.getIndex(), page.getPageSize());
        page.setPageData(pageList);
        return page;
    }

    /**
     * 查询价格在minPrice和maxPrice中的记录，并且分页查询
     *
     * @param pageNo   当前页码
     * @param pageSize 每页的大小
     * @param minPrice 最小价格
     * @param maxPrice 最大价格
     * @return 分页记录对象
     */
    @Override
    public Page<Book> getPageByPrice(String pageNo, String pageSize, String minPrice, String maxPrice) {
        // 页码
        int pn = 1;
        // 每页的记录数
        int ps = 4;
        double min = 0.0;
        double max = Double.MAX_VALUE;
        try {
            pn = Integer.parseInt(pageNo);
            ps = Integer.parseInt(pageSize);
            min = Double.parseDouble(minPrice);
            max = Double.parseDouble(maxPrice);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        Page<Book> page = new Page<>();
        // 查询设置总记录数
        page.setTotalCount(bookDao.getTotalCountByPrice(min, max));
        // 设置每页大小
        page.setPageSize(ps);
        // 设置当前页面
        page.setPageNo(pn);

        // 查询分页数据
        List<Book> pageList = bookDao.getPageListByPrice(page.getIndex(), page.getPageSize(), min, max);
        page.setPageData(pageList);
        return page;
    }
}
