package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
