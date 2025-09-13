package com.YuTing.commerce.admin.service.dtos.requests;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private int userId;  // 哪個使用者下單
    private String shippingName;
    private String shippingAddress;

    private List<OrderItemRequest> items; // 商品明細
}
