package com.YuTing.commerce.admin.service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    //1.cascade = CascadeType.ALL → 當你存 Order 時，OrderItem 會自動跟著存。
    //2.orphanRemoval = true → 如果從 orderItems 移除某個 item，它會自動從資料庫刪掉（避免殘留垃圾資料）。
    //3.初始化為 new ArrayList<>() 可以避免 NullPointerException。


    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    //1.金額最好用 BigDecimal（避免浮點數誤差，雖然你現在用 int 表示台幣沒問題，但未來支援小數或不同幣別會更安全）。
    //2.加上 nullable = false 可以確保不會存 null。

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    //1.updatable = false 保證建立後不會被修改。
    //2.初始化 LocalDateTime.now()，避免忘記設定。

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
