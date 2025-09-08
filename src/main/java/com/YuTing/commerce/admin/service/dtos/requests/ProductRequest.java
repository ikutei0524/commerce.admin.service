package com.YuTing.commerce.admin.service.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private Integer categoryId;
    private String imageThumbnail;
    private String imageUrl;
    private String description;
    private Double width;
    private Double height;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
}
