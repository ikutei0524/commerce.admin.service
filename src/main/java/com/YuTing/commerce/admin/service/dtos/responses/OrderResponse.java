package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private int id;
    private int userId;
    private String shippingName;
    private String shippingAddress;
    private BigDecimal totalPrice;
    private String status;
    private Boolean returned;
    private LocalDateTime createdAt;

    private List<OrderItemResponse> items;
}
