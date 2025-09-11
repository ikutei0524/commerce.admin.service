package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.dtos.requests.OrderRequest;
import com.YuTing.commerce.admin.service.dtos.responses.OrderResponse;
import com.YuTing.commerce.admin.service.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Orders")
@RequiredArgsConstructor
@Tag(name = "訂單管理介面", description = "管理訂單相關 API")
public class OrderController {

    private final OrderService orderService;

    // Create
    @PostMapping
    @Operation(
            summary = "建立新訂單(CreateOrder)",
            description = "建立一筆新的訂單，包含使用者與訂單商品資訊"
    )
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    // Read all
    @GetMapping
    @Operation(
            summary = "取得所有訂單(FindAllOrders)",
            description = "回傳所有訂單的清單 (List<OrderResponse>)"
    )
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Read by id
    @GetMapping("/{id}")
    @Operation(
            summary = "取得指定 ID 的訂單(FindOrderById)",
            description = "根據傳入的訂單 ID，回傳單一訂單資料"
    )
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // Update
    @PutMapping("/{id}")
    @Operation(
            summary = "更新訂單(UpdateOrderById)",
            description = "根據指定 ID 更新訂單資訊，回傳更新後的訂單資料"
    )
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Integer id,
                                                     @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

    // Delete
    @DeleteMapping("/{id}")
    @Operation(
            summary = "刪除訂單(DeleteOrderById)",
            description = "根據指定 ID 刪除訂單"
    )
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
