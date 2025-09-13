package com.YuTing.commerce.admin.service.dtos.responses;

import com.YuTing.commerce.admin.service.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponse {
    private Integer id;   // Product 主鍵
    private CategoryProductResponse category;
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
