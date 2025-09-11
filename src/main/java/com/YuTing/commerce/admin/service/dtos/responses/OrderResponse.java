package com.YuTing.commerce.admin.service.dtos.responses;

import com.YuTing.commerce.admin.service.model.enums.OrderStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Integer id;
    private Integer userId;
    private OrderStatusEnum status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;


}
