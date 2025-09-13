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
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "訂單介面", description = "訂單管理 API")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(
            summary = "建立訂單",
            description = "建立訂單清單"
    )
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "取得指定訂單Id的清單",
            description = "回傳指定訂單Id的清單清單"
    )
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "取得指定使用者Id的清單",
            description = "回傳指定使用者Id的歷史訂單"
    )
    public ResponseEntity<List<OrderResponse>> getOrdersByUser(@PathVariable int userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }
}