package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.dtos.enums.StockFilter;
import com.YuTing.commerce.admin.service.dtos.requests.ProductRequest;
import com.YuTing.commerce.admin.service.dtos.responses.ProductResponse;
import com.YuTing.commerce.admin.service.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "產品管理介面", description = "管理產品相關 API")
@SecurityRequirement(name = "bearerAuth")
//以下只是方法,邏輯盡數放在Service裡
public class ProductController {

    private final ProductService productService;




    @GetMapping
    @Operation(
            summary = "取得所有產品(FindAllProducts)",
            description = "回傳所有產品的清單 (List<ProductResponse>)"
    )
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String queryName,
                                                @RequestParam(required = false) Integer categoryId,
                                                @RequestParam(required = false) StockFilter stockFilter)
    {
        return productService.getAllProducts(queryName, categoryId,
                stockFilter != null ? stockFilter.getStockFrom() : null,
                stockFilter != null ? stockFilter.getStockTo() : null);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "取得指定 ID 的產品(FindProductById))",
            description = "根據傳入的產品ID，回傳單一產品資料"
    )
    public ProductResponse getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @Operation(
            summary = "新增產品(CreateNewProduct)",
            description = "建立一個新的產品，並回傳建立後的產品資料"
    )
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "更新產品資料(UpdateProductById)",
            description = "根據指定 ID 更新產品的資訊，回傳更新後的產品資料"
    )
    public ProductResponse updateProduct(@PathVariable Integer id,
                                         @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "刪除產品",
            description = "根據指定ID將產品標記為已刪除"
    )
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/page")
    @Operation(
            summary = "產品分頁",
            description = "以分頁形式取得產品資訊"
    )
    public ResponseEntity<Page<ProductResponse>> getProductPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ProductResponse> productResponses = productService.getProductPage(page, size);
        return ResponseEntity.ok(productResponses);
    }


}
