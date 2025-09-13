package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.dtos.requests.ProductRequest;
import com.YuTing.commerce.admin.service.dtos.responses.CategoryProductResponse;
import com.YuTing.commerce.admin.service.dtos.responses.ProductResponse;
import com.YuTing.commerce.admin.service.mappers.ProductMapper;
import com.YuTing.commerce.admin.service.model.Category;
import com.YuTing.commerce.admin.service.model.Product;
import com.YuTing.commerce.admin.service.repositories.CategoryRepository;
import com.YuTing.commerce.admin.service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public java.util.Optional<Product> getProductEntityById(Integer id) {
        return productRepository.findById(id);
    }

    public ProductResponse getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToResponse(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = ProductMapper.toEntity(request, category);
        return ProductMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse updateProduct(Integer id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ProductMapper.updateEntity(product, request, category);
        return ProductMapper.toResponse(productRepository.save(product));
    }

    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    private ProductResponse mapToResponse(Product product) {
        CategoryProductResponse categoryResponse = new CategoryProductResponse(
                product.getCategory().getId(),
                product.getCategory().getName() // 對應 reference
        );

        return new ProductResponse(
                product.getId(),
                categoryResponse,   // 改成 DTO
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
