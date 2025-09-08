package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.dtos.requests.CategoryRequest;
import com.YuTing.commerce.admin.service.dtos.responses.CategoryResponse;
import com.YuTing.commerce.admin.service.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Integer id,
                                           @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
