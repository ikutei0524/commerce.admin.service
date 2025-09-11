package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.dtos.requests.OrderRequest;
import com.YuTing.commerce.admin.service.dtos.responses.OrderResponse;
import com.YuTing.commerce.admin.service.mappers.OrderMapper;
import com.YuTing.commerce.admin.service.model.Order;
import com.YuTing.commerce.admin.service.model.Product;
import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.repositories.OrderRepository;
import com.YuTing.commerce.admin.service.repositories.ProductRepository;
import com.YuTing.commerce.admin.service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    // 🟢 Create
    public OrderResponse createOrder(OrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 一次撈出需要的所有 products
        List<Integer> productIds = request.getItems().stream()
                .map(item -> item.getProductId())
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        // 轉成 Order entity
        Order order = orderMapper.toEntity(request, user, products);

        // 計算 totalPrice
        BigDecimal totalPrice = order.getOrderItems().stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    // 🔵 Read all
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 🔵 Read by id
    public OrderResponse getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toResponse(order);
    }

    // 🟡 Update
    public OrderResponse updateOrder(Integer id, OrderRequest request) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 一次撈出需要的 products
        List<Integer> productIds = request.getItems().stream()
                .map(item -> item.getProductId())
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        // 覆蓋成新的 entity
        Order updatedOrder = orderMapper.toEntity(request, user, products);
        updatedOrder.setId(existingOrder.getId());

        // 重新計算 totalPrice
        BigDecimal totalPrice = updatedOrder.getOrderItems().stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        updatedOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(updatedOrder);
        return orderMapper.toResponse(savedOrder);
    }

    // 🔴 Delete
    public void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}