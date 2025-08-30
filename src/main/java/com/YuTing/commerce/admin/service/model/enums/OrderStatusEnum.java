package com.YuTing.commerce.admin.service.model.enums;

public class OrderStatusEnum {



    public enum OrderStatus{
        PENDING,     // 待付款
        PAID,        // 已付款
        SHIPPED,     // 已出貨
        COMPLETED,   // 已完成
        CANCELLED    // 已取消
    }
}
