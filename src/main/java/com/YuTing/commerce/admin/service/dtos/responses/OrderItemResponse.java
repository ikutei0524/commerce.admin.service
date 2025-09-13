package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private int productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
}
