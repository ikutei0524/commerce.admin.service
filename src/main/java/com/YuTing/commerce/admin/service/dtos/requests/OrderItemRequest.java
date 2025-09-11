package com.YuTing.commerce.admin.service.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequest {
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;


}