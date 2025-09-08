package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.requests.CategoryRequest;
import com.YuTing.commerce.admin.service.dtos.responses.CategoryResponse;
import com.YuTing.commerce.admin.service.model.Category;

import java.time.LocalDateTime;

public class CategoryMapper {

    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getDeletedAt()
        );
    }

    public static Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setCreatedAt(LocalDateTime.now());
        return category;
    }

    public static void updateEntity(Category category, CategoryRequest request) {
        category.setName(request.getName());
        category.setDeletedAt(null); // 避免更新時誤蓋掉
    }
}
