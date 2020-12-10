package com.atguigu.bean;

import java.util.Date;

/**
 * 订单类
 */
public class Order {
    // 订单id
    private String orderId;
    // 创建时间
    private Date createDate;
    // 总价
    private Double totalPrice;
    // 订单状态
    private Integer status;
    // 订单所属用户的id
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createDate, double totalPrice, int status, int userId) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
