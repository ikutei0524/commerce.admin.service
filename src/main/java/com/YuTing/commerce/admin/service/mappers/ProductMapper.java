package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.requests.ProductRequest;
import com.YuTing.commerce.admin.service.dtos.responses.CategoryProductResponse;
import com.YuTing.commerce.admin.service.dtos.responses.ProductResponse;
import com.YuTing.commerce.admin.service.model.Category;
import com.YuTing.commerce.admin.service.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    // 將 Product Entity 轉成 Response
    public static ProductResponse toResponse(Product product) {
        CategoryProductResponse categoryDto = null;
        if (product.getCategory() != null) {
            categoryDto = new CategoryProductResponse(
                    product.getCategory().getId(),
                    product.getCategory().getName() // reference 就是 name
            );
        }
        return new ProductResponse(
                product.getId(),
                categoryDto,
                product.getImageThumbnail(),
                product.getImageUrl(),
                product.getDescription(),
                product.getWidth(),
                product.getHeight(),
                product.getPrice(),
                product.getSales(),
                product.getStock(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    // 將 Request + Category 轉成 Entity (建立新 Product 用)
    public static Product toEntity(ProductRequest request, Category category) {
        Product product = new Product();
        product.setCategory(category);// 綁上 Category
        product.setImageThumbnail(request.getImageThumbnail());
        product.setImageUrl(request.getImageUrl());
        product.setDescription(request.getDescription());
        product.setWidth(request.getWidth());
        product.setHeight(request.getHeight());
        product.setPrice(request.getPrice());
        product.setSales(request.getSales());
        product.setStock(request.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

    // 更新 Product (Edit 用)
    public static void updateEntity(Product product, ProductRequest request, Category category) {
        product.setCategory(category);// 綁上 Category
        product.setImageThumbnail(request.getImageThumbnail());
        product.setImageUrl(request.getImageUrl());
        product.setDescription(request.getDescription());
        product.setWidth(request.getWidth());
        product.setHeight(request.getHeight());
        product.setPrice(request.getPrice());
        product.setSales(request.getSales());
        product.setStock(request.getStock());
        product.setUpdatedAt(LocalDateTime.now());
    }
}
