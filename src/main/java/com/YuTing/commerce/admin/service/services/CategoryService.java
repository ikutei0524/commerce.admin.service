package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.dtos.requests.CategoryRequest;
import com.YuTing.commerce.admin.service.dtos.responses.CategoryResponse;
import com.YuTing.commerce.admin.service.mappers.CategoryMapper;
import com.YuTing.commerce.admin.service.model.Category;
import com.YuTing.commerce.admin.service.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toResponse(category);
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = CategoryMapper.toEntity(request);
        return CategoryMapper.toResponse(categoryRepository.save(category));
    }

    public CategoryResponse updateCategory(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        CategoryMapper.updateEntity(category, request);
        return CategoryMapper.toResponse(categoryRepository.save(category));
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setDeletedAt(LocalDateTime.now());
        categoryRepository.save(category);
    }
}
