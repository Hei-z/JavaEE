package com.atguigu.service;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

import java.util.List;

public interface BookService {
    /**
     * 获取一本图书
     */
    Book getOne(Book book);

    /**
     * 获取所有图书
     */
    List<Book> getAll();

    /**
     * 添加图书
     */
    boolean add(Book book);

    /**
     * 删除图书
     */
    boolean delete(Book book);

    /**
     * 修改图书
     */
    boolean update(Book book);

    /**
     * @param pageNo   page number
     * @param pageSize the number of records in evey page
     * @return Page Object
     */
    Page<Book> getPage(String pageNo, String pageSize);

    /**
     * 根据价格来查询分页数据
     *
     * @param pageNo
     * @param pageSize
     * @param minPrice
     * @param maxPrice
     * @return
     */
    Page<Book> getPageByPrice(String pageNo, String pageSize, String minPrice, String maxPrice);
}
