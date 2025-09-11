package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.requests.OrderRequest;
import com.YuTing.commerce.admin.service.dtos.requests.OrderItemRequest;
import com.YuTing.commerce.admin.service.dtos.responses.OrderResponse;
import com.YuTing.commerce.admin.service.dtos.responses.OrderItemResponse;
import com.YuTing.commerce.admin.service.model.Order;
import com.YuTing.commerce.admin.service.model.OrderItem;
import com.YuTing.commerce.admin.service.model.Product;
import com.YuTing.commerce.admin.service.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    /**
     * 將 OrderRequest 轉換成 Order Entity
     * 這裡要注意：products 必須在 Service 層事先從 DB 撈出來再傳進來
     */
    public Order toEntity(OrderRequest request, User user, List<Product> products) {
        Order order = new Order();
        order.setUser(user);

        if (request.getItems() != null) {
            order.setOrderItems(
                    request.getItems().stream()
                            .map(itemReq -> {
                                Product product = products.stream()
                                        .filter(p -> p.getId().equals(itemReq.getProductId()))
                                        .findFirst()
                                        .orElseThrow(() -> new RuntimeException("Product not found: " + itemReq.getProductId()));

                                return toOrderItemEntity(itemReq, order, product);
                            })
                            .collect(Collectors.toList())
            );
        }
        return order;
    }

    private OrderItem toOrderItemEntity(OrderItemRequest itemReq, Order order, Product product) {
        OrderItem item = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(itemReq.getQuantity())
                .build();

        // ✅ 下單時鎖定價格
        item.setPrice(product.getPrice());
        return item;
    }

    /**
     * 將 Order Entity 轉換成 OrderResponse
     */
    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setCreatedAt(order.getCreatedAt());
        response.setStatus(order.getStatus());
        response.setTotalPrice(order.getTotalPrice());

        if (order.getOrderItems() != null) {
            response.setItems(
                    order.getOrderItems().stream()
                            .map(this::toItemResponse)
                            .collect(Collectors.toList())
            );
        }
        return response;
    }

    private OrderItemResponse toItemResponse(OrderItem item) {
        OrderItemResponse response = new OrderItemResponse();
        response.setId(item.getId());
        response.setProductId(item.getProduct().getId());
        response.setQuantity(item.getQuantity());
        response.setPrice(item.getPrice());
        return response;
    }
}