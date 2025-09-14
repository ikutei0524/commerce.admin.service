package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.dtos.enums.OrderStatus;
import com.YuTing.commerce.admin.service.dtos.requests.OrderItemRequest;
import com.YuTing.commerce.admin.service.dtos.requests.OrderRequest;
import com.YuTing.commerce.admin.service.dtos.responses.OrderResponse;
import com.YuTing.commerce.admin.service.mappers.OrderMapper;
import com.YuTing.commerce.admin.service.model.*;
import com.YuTing.commerce.admin.service.repositories.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setShippingName(request.getShippingName());
        order.setShippingAddress(request.getShippingAddress());
        order.setStatus(OrderStatus.PENDING);
        order.setReturned(false);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            item.setPrice(subtotal);
            sum = sum.add(subtotal);
            items.add(item);
        }

        order.setOrderItems(items);

        // 🚀 新增運費 & 稅金邏輯（假設固定運費、固定稅率）
        BigDecimal delivery = new BigDecimal("7.16"); // 或者從配置 / request 帶進來
        BigDecimal taxRate = new BigDecimal("0.17");  // 17%
        BigDecimal tax = sum.multiply(taxRate);

        // 設定到 Entity
        order.setDelivery(delivery);
        order.setTax(tax);

        // 最後總價
        BigDecimal total = sum.add(delivery).add(tax);
        order.setTotalPrice(total);

        Order saved = orderRepository.save(order);
        return OrderMapper.toResponse(saved);
    }

    @Override
    public OrderResponse getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(OrderMapper::toResponse)
                .toList();
    }



    @Override
    public Page<OrderResponse> getOrderPage(int page, int size, String query) {
        PageRequest pageRequest = PageRequest.of(page, size);

        // 動態查詢條件
        Specification<Order> spec = (root, q, cb) -> {
            if (query == null || query.isEmpty()) {
                return cb.conjunction();
            }
            String likeQuery = "%" + query.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("shippingName")), likeQuery),
                    cb.like(cb.lower(root.get("shippingAddress")), likeQuery),
                    cb.like(cb.lower(root.get("status")), likeQuery)
            );
        };

        // 執行查詢 + 分頁
        Page<Order> ordersPage = orderRepository.findAll(spec, pageRequest);

        // 轉換成 OrderResponse
        return ordersPage.map(OrderMapper::toResponse);
    }


}
