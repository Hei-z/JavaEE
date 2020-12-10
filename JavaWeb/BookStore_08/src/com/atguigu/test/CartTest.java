package com.atguigu.test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import org.junit.Test;

public class CartTest {
    @Test
    public void test() {
        Cart cart = new Cart();
        cart.addItem2Cart(new Book(1, "a", "a", 2.2, 2, 3, "xx"));
        cart.addItem2Cart(new Book(1, "a", "a", 2.2, 2, 3, "xx"));
        cart.addItem2Cart(new Book(2, "a", "a", 2.2, 2, 3, "xx"));
        cart.addItem2Cart(new Book(3, "a", "a", 2.2, 2, 3, "xx"));
        System.out.println(cart.getTotalPrice());
    }

    private static int process() {
        try {
            int a = 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public static void main(String[] args) {
        int res = process();
        System.out.println(res);
    }
}
