package com.atguigu.test;

import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

public class BookServiceTest {

    @Test
    public void test1() {
        BookService bookService = new BookServiceImpl();
        System.out.println(bookService.getPageByPrice("0", "100", "5", "50").getPageData().size());
    }
}
