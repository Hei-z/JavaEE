package com.atguigu.bean;

import java.math.BigDecimal;

/**
 * 购物项类
 */
public class CartItem {
    // 书本信息
    private Book book;
    // 书本数量
    private int count;
    // 总价格
    private double totalPrice;

    public CartItem() {
    }

    public CartItem(Book book, int count, double totalPrice) {
        this.book = book;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 采用大数运算，避免double的精度问题
     *
     * @return 返回该购物项的总价格
     */
    public double getTotalPrice() {
        BigDecimal bd = new BigDecimal(book.getPrice() + "");
        return bd.multiply(new BigDecimal(count + "")).doubleValue();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
