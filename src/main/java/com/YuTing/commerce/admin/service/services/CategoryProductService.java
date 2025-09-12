package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.dtos.responses.CategoryProductResponse;
import com.YuTing.commerce.admin.service.dtos.responses.ProductResponse;
import com.YuTing.commerce.admin.service.model.Category;
import com.YuTing.commerce.admin.service.model.Product;
import com.YuTing.commerce.admin.service.repositories.CategoryRepository;
import com.YuTing.commerce.admin.service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // 取得分類下所有產品
    public List<ProductResponse> getProductsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Product> products = productRepository.findByCategoryId(categoryId);

        return products.stream()
                .map(product -> mapToResponse(product, category))
                .collect(Collectors.toList());
    }

    // 將多個商品加入同一分類
    public void addProductsToCategory(Integer categoryId, List<Integer> productIds) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Product> products = productRepository.findAllById(productIds);

        products.forEach(product -> product.setCategory(category));

        productRepository.saveAll(products);
    }

    // 將多個商品從分類移除
    public void removeProductsFromCategory(List<Integer> productIds) {
        List<Product> products = productRepository.findAllById(productIds);

        products.forEach(product -> product.setCategory(null));

        productRepository.saveAll(products);
    }

    // DTO 映射
    private ProductResponse mapToResponse(Product product, Category category) {
        CategoryProductResponse categoryResponse = new CategoryProductResponse(
                category.getId(),
                category.getName()
        );

        return new ProductResponse(
                product.getId(),
                categoryResponse,
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
}
