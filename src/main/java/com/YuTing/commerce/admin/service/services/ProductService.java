package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.dtos.enums.StockFilter;
import com.YuTing.commerce.admin.service.dtos.requests.ProductRequest;
import com.YuTing.commerce.admin.service.dtos.responses.CategoryProductResponse;
import com.YuTing.commerce.admin.service.dtos.responses.ProductResponse;
import com.YuTing.commerce.admin.service.mappers.ProductMapper;
import com.YuTing.commerce.admin.service.model.Category;
import com.YuTing.commerce.admin.service.model.Product;
import com.YuTing.commerce.admin.service.repositories.CategoryRepository;
import com.YuTing.commerce.admin.service.repositories.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductResponse> getAllProducts(String query, Integer categoryId, Integer stockFrom, Integer stockTo) {
        Specification<Product> spec = productSpecification(query, categoryId, stockFrom, stockTo);
        return productRepository.findAll(spec).stream()
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
                product.getCategory().getName()
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

    public Page<ProductResponse> getProductPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return productRepository.findAll(pageRequest)
                .map(this::mapToResponse);
    }

    // 🔹 新增：支援查詢條件 (名稱 / 分類 / 庫存範圍)
    public List<ProductResponse> getFilteredProducts(String queryName, Integer categoryId, StockFilter stockFilter) {
        Integer stockFrom = stockFilter != null ? stockFilter.getStockFrom() : null;
        Integer stockTo   = stockFilter != null ? stockFilter.getStockTo()   : null;

        Specification<Product> spec = productSpecification(queryName, categoryId, stockFrom, stockTo);

        return productRepository.findAll(spec)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private Specification<Product> productSpecification(String queryName, Integer categoryId, Integer stockFrom, Integer stockTo) {
        return (((root, query, criteriaBuilder) ->  {
            List<Predicate> predicates = new ArrayList<>();
            if(queryName != null && !queryName.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("reference")), "%"+ queryName.toLowerCase()+"%")
                ));
            }

            if(categoryId != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId));
            }

            if(stockFrom != null && stockTo == null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stockFrom));
            }

            if(stockFrom != null && stockTo != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stockFrom));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("stock"), stockTo));
            }

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
        }));
    }




}
