package com.YuTing.commerce.admin.service.dtos.requests;

import lombok.Data;

@Data
public class OrderItemRequest {
    private int productId; // 商品 ID
    private int quantity;  // 購買數量
}
