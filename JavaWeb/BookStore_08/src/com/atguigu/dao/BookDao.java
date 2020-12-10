package com.atguigu.dao;

import com.atguigu.bean.Book;

import java.util.List;


public interface BookDao {
    /**
     * 获取所有的图书
     *
     * @return all books list
     */
    List<Book> getAllBook();

    /**
     * add one book
     *
     * @param book the book need to be added
     * @return if add successfully return true else return false
     */
    boolean addBook(Book book);

    /**
     * 删除一本图书
     *
     * @param book 删除的图书
     * @return 如果删除成功返回true
     */
    boolean delBook(Book book);

    /**
     * 修改图书
     *
     * @param book 需要修改的图书
     */
    boolean updateBook(Book book);

    /**
     * 获取一本图书的详细信息
     *
     * @param book 包含图书id
     * @return
     */
    Book getBook(Book book);

    /**
     * 查询分页数据 Query paging data
     * select * from table limit index, pageSize
     *
     * @param index    start index
     * @param pageSize the number of records
     */
    List<Book> getPageList(int index, int pageSize);

    /**
     * query paging data between minPrice and maxPirce
     *
     * @param index    start index in select statement
     * @param pageSize the number of records
     * @param minPrice min price
     * @param maxPrice max price
     */
    List<Book> getPageListByPrice(int index, int pageSize, double minPrice, double maxPrice);

    /**
     * get the total count of all data
     */
    int getTotalCount();

    /**
     * get the number of records between min price and max price
     *
     * @param minPrice min price
     * @param maxPrice max price
     */
    int getTotalCountByPrice(double minPrice, double maxPrice);
}
