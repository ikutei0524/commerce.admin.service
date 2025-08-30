package com.YuTing.commerce.admin.service.model;


import com.YuTing.commerce.admin.service.model.enums.OrderStatusEnum;//使用enums裡的OrderStatus
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    //多對一:order->user

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;
    //一對多:order->orderItems

    @Column(name = "user_id")
    private int userId;

    @Column(name = "total_price")
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatusEnum status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;












}
