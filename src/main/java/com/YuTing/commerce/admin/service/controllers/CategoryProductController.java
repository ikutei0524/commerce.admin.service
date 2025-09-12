package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.dtos.responses.ProductResponse;
import com.YuTing.commerce.admin.service.services.CategoryProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category-products")
public class CategoryProductController {

    @Autowired
    private CategoryProductService categoryProductService;

    @Operation(
            summary = "查詢分類下所有商品",
            description = "透過分類 ID，查詢該分類底下的所有商品清單。"
    )
    @GetMapping("/{categoryId}/products")
    public List<ProductResponse> getProductsByCategory(@PathVariable Integer categoryId) {
        return categoryProductService.getProductsByCategory(categoryId);
    }

    @Operation(
            summary = "多個商品加入分類",
            description = "將多個商品（以商品 ID 陣列表示）加入到指定的分類中。"
    )
    @PostMapping("/{categoryId}/products")
    public void addProductsToCategory(@PathVariable Integer categoryId,
                                      @RequestBody List<Integer> productIds) {
        categoryProductService.addProductsToCategory(categoryId, productIds);
    }

    @Operation(
            summary = "多個商品從分類移除",
            description = "將多個商品（以商品 ID 陣列表示）從其原本的分類移除，移除後商品將不再屬於任何分類。"
    )
    @DeleteMapping("/products")
    public void removeProductsFromCategory(@RequestBody List<Integer> productIds) {
        categoryProductService.removeProductsFromCategory(productIds);
    }
}
