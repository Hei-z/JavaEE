package com.atguigu.bean;

/**
 * 订单项类
 */
public class OrderItem {
    // 订单项id
    private Integer id;
    // 书名
    private String title;
    // 单价
    private Double price;
    // 图书总价
    private Double totalPrice;
    // 图书数量
    private Integer count;
    // 所属订单
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(Integer id, String title, Double price, Double totalPrice, Integer count, String orderId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.totalPrice = totalPrice;
        this.count = count;
        this.orderId = orderId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", count=" + count +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
