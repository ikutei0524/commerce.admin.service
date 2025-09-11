package com.YuTing.commerce.admin.service.dtos.requests;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Integer userId; // 下單人
    private List<OrderItemRequest> items; // 訂單明細
}