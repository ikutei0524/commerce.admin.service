package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.dtos.responses.ProductResponse;
import com.YuTing.commerce.admin.service.services.CategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category-products")
public class CategoryProductController {

    @Autowired
    private CategoryProductService categoryProductService;

    // 查詢分類下所有商品
    @GetMapping("/{categoryId}/products")
    public List<ProductResponse> getProductsByCategory(@PathVariable Integer categoryId) {
        return categoryProductService.getProductsByCategory(categoryId);
    }

    // 多個商品加入分類
    @PostMapping("/{categoryId}/products")
    public void addProductsToCategory(@PathVariable Integer categoryId,
                                      @RequestBody List<Integer> productIds) {
        categoryProductService.addProductsToCategory(categoryId, productIds);
    }

    // 多個商品從分類移除
    @DeleteMapping("/products")
    public void removeProductsFromCategory(@RequestBody List<Integer> productIds) {
        categoryProductService.removeProductsFromCategory(productIds);
    }
}
