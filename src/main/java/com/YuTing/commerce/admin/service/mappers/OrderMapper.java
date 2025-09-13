package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.responses.OrderItemResponse;
import com.YuTing.commerce.admin.service.dtos.responses.OrderResponse;
import com.YuTing.commerce.admin.service.model.Order;
import com.YuTing.commerce.admin.service.model.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setShippingName(order.getShippingName());
        response.setShippingAddress(order.getShippingAddress());
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus().name());
        response.setReturned(order.getReturned());
        response.setCreatedAt(order.getCreatedAt());

        response.setItems(
                order.getOrderItems().stream()
                        .map(OrderMapper::toItemResponse)
                        .collect(Collectors.toList())
        );
        return response;
    }

    private static OrderItemResponse toItemResponse(OrderItem item) {
        OrderItemResponse response = new OrderItemResponse();
        response.setProductId(item.getProduct().getId());
        response.setProductName(item.getProduct().getDescription()); // 或 name 欄位
        response.setQuantity(item.getQuantity());
        response.setPrice(item.getProduct().getPrice());
        response.setSubtotal(item.getProduct().getPrice().multiply(
                java.math.BigDecimal.valueOf(item.getQuantity())));
        return response;
    }
}
