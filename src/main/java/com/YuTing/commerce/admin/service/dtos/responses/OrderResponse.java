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

    private BigDecimal sum;       // 商品總額(商品合計總額)
    private BigDecimal delivery;  // 運費
    private BigDecimal tax;       // 稅金
    private BigDecimal total;    //總價

    private String status;
    private Boolean returned;
    private LocalDateTime createdAt;

    private List<OrderItemResponse> items;
}
