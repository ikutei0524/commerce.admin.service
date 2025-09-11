package com.YuTing.commerce.admin.service.dtos.requests;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Integer productId;
    private Integer quantity;
}