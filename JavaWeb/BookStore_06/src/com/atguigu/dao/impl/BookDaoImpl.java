package com.atguigu.dao.impl;

import com.atguigu.bean.Book;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> getAllBook() {
        // img_path imgPath此处要其别名，因为BaseDao中封装对象时，数据库字段名和JavaBean的属性名要一样
        String sql = "select id, title, author, price, sales, stock, img_path imgPath from bs_book";
        return getBeanList(sql);
    }

    @Override
    public boolean addBook(Book book) {
        String sql = "insert into bs_book(title, author, price, sales, stock, img_path) values(?,?,?,?,?,?)";
        return update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getSales(), book.getImgPath()) > 0;
    }

    @Override
    public boolean delBook(Book book) {
        String sql = "delete from bs_book where id = ?";
        return update(sql, book.getId()) > 0;
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "update bs_book set title=?, author=?, price=?, sales=?, stock=?, img_path=? where id = ?";
        return update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId()) > 0;
    }

    @Override
    public Book getBook(Book book) {
        String sql = "select id, title, author, price, sales, stock, img_path imgPath from bs_book" +
                " where id = ?";
        return getBean(sql, book.getId());
    }

    @Override
    public List<Book> getPageList(int index, int pageSize) {
        String sql = "select id, title, author, price, sales, stock, img_path imgPath from bs_book limit ?, ?";
        return getBeanList(sql, index, pageSize);
    }

    @Override
    public List<Book> getPageListByPrice(int index, int pageSize, double minPrice, double maxPrice) {
        String sql = "select id, title, author, price, sales, stock, img_path imgPath from bs_book " +
                "where price between ? and ? limit ?, ?";
        return getBeanList(sql, minPrice, maxPrice, index, pageSize);
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from bs_book";
        return ((Long) getSingleValue(sql)).intValue();
    }

    @Override
    public int getTotalCountByPrice(double minPrice, double maxPrice) {
        String sql = "select count(*) from bs_book where price between ? and ?";
        return ((Long) getSingleValue(sql, minPrice, maxPrice)).intValue();
    }

}
