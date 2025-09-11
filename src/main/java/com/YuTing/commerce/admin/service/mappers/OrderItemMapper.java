package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.requests.OrderItemRequest;
import com.YuTing.commerce.admin.service.dtos.responses.OrderItemResponse;
import com.YuTing.commerce.admin.service.model.Order;
import com.YuTing.commerce.admin.service.model.OrderItem;
import com.YuTing.commerce.admin.service.model.Product;

public class OrderItemMapper {

    public static OrderItemResponse toResponse(OrderItem item) {
        if (item == null) return null;

        OrderItemResponse dto = new OrderItemResponse();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct() != null ? item.getProduct().getId() : null);
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    public static OrderItem toEntity(OrderItemRequest request, Product product, Order order) {
        if (request == null) return null;

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        item.setPrice(request.getPrice());
        return item;
    }
}
