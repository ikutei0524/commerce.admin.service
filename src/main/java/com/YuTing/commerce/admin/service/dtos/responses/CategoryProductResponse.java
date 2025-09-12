package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductResponse {
    private Integer id;
    private String reference; // 可以對應 Category.name
}
