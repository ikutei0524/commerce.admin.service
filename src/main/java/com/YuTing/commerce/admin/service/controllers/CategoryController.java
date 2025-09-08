package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.dtos.requests.CategoryRequest;
import com.YuTing.commerce.admin.service.dtos.responses.CategoryResponse;
import com.YuTing.commerce.admin.service.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Categories")
@RequiredArgsConstructor
@Tag(name = "產品分類介面", description = "管理產品分類相關API")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "查詢所有分類")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "查詢某產品分類的所有產品分類")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @Operation(summary = "新增產品分類")
    public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改所填ID的產品分類資訊")
    public CategoryResponse updateCategory(@PathVariable Integer id,
                                           @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除指定ID的產品分類")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
