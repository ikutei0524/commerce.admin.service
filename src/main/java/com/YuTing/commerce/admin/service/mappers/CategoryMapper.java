package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.requests.CategoryRequest;
import com.YuTing.commerce.admin.service.dtos.responses.CategoryResponse;
import com.YuTing.commerce.admin.service.model.Category;

public class CategoryMapper {

    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getDeletedAt()
        );
    }

    public static Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setCreatedAt(java.time.LocalDateTime.now());
        return category;
    }


}
