package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private Integer categoryId;// 綁定 Category
    private String imageThumbnail;
    private String imageUrl;
    private String description;
    private Double width;
    private Double height;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
