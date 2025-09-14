package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.responses.OrderItemResponse;
import com.YuTing.commerce.admin.service.dtos.responses.OrderResponse;
import com.YuTing.commerce.admin.service.model.Order;
import com.YuTing.commerce.admin.service.model.OrderItem;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setShippingName(order.getShippingName());
        response.setShippingAddress(order.getShippingAddress());
        response.setStatus(order.getStatus().name());
        response.setReturned(order.getReturned());
        response.setCreatedAt(order.getCreatedAt());

        //計算 Sum
        BigDecimal sum = order.getOrderItems().stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //Order entity 有 delivery、tax 欄位
        BigDecimal delivery = order.getDelivery() != null ? order.getDelivery() : BigDecimal.ZERO;
        BigDecimal tax = order.getTax() != null ? order.getTax() : BigDecimal.ZERO;

        //總價 (Total)
        BigDecimal total = sum.add(delivery).add(tax);

        response.setSum(sum);
        response.setDelivery(delivery);
        response.setTax(tax);
        response.setTotal(total);

        //Items
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
        response.setProductName(item.getProduct().getDescription()); // 或改成 product.getName()
        response.setQuantity(item.getQuantity());
        response.setPrice(item.getProduct().getPrice());
        response.setSubtotal(
                item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
        );
        return response;
    }
}
