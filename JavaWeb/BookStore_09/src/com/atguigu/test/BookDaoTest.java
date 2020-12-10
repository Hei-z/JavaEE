package com.atguigu.test;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import org.junit.Test;

public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();

    @Test
    public void test1() {
        System.out.println(bookDao.getAllBook());
    }

    @Test
    public void test2() {
        // (Integer id, String title, String author, Double price, Integer sales, Integer stock, String imgPath)
        Book book = new Book(null, "c++", "zs", 20.0, 2, 22, "static/img/default.jpg");
        System.out.println(bookDao.addBook(book));
    }

    @Test
    public void test3() {
        Book book = new Book();
        book.setId(2);
        System.out.println(bookDao.delBook(book));
    }

    @Test
    public void test4() {
        Book book = new Book(1, "c++", "zs", 20.0, 2, 22, "static/img/default.jpg");
        bookDao.updateBook(book);
    }

    @Test
    public void test5() {
        Book book = new Book();
        book.setId(1);
        System.out.println(bookDao.getBook(book));
    }

}
