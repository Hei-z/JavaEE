package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.*;

/**
 * 购物车类
 */
public class Cart {
    // key为购物项的id，value为具体购物项的信息
    private Map<Integer, CartItem> items = new LinkedHashMap<>();
    // 购物项总数

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * @return 返回购物车中所有的购物项，用于前台显示
     */
    public List<CartItem> getItems() {
        Collection<CartItem> values = items.values();
        return new ArrayList<>(values);
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * @return 返回购物车中商品的总数
     */
    public int getTotalCount() {
        int count = 0;
        for (CartItem cartItem : items.values()) {
            count += cartItem.getCount();
        }
        return count;
    }


    /**
     * 采用大数运算，避免double的精度问题
     *
     * @return 返回购物车中商品的总价格
     */
    public double getTotalPrice() {
        BigDecimal bd = new BigDecimal("0");
        for (CartItem cartItem : items.values()) {
            bd = bd.add(new BigDecimal(cartItem.getTotalPrice() + ""));
        }
        return bd.doubleValue();
    }


    /**
     * 向购物车中添加商品
     */
    public void addItem2Cart(Book book) {
        CartItem item = items.getOrDefault(book.getId(), new CartItem());
        item.setBook(book);
        item.setCount(item.getCount() + 1);
        item.setTotalPrice(item.getTotalPrice() + book.getPrice());
        items.put(book.getId(), item);
    }

    /**
     * 删除购物车中一项
     *
     * @param bookId 图书id
     */
    public void deleteItem(String bookId) {
        items.remove(Integer.parseInt(bookId));
    }

    /**
     * 修改购物车中商品的数量
     *
     * @param bookId   图书id
     * @param countStr 新的数量
     */
    public void updateCount(String bookId, String countStr) {
        int id = 0;
        try {
            id = Integer.parseInt(bookId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int count = 1;
        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (count <= 0) {
            items.remove(id);
        } else {
            CartItem cartItem = items.get(id);
            if (cartItem != null) {
                cartItem.setCount(count);
            }
        }
    }

    /**
     * 获取某一个购物项
     */
    public CartItem getItem(String bookId) {
        int id = Integer.parseInt(bookId);
        return items.get(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
