package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;
}