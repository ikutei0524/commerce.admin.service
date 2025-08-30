package com.YuTing.commerce.admin.service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    private Review review;

    @ManyToOne
    private Categorie categories;

    @ManyToMany
    private OrderItem orderItem;

    @Column(name = "categories_id")
    private int categoriesId;

    @Column(name = "image_thumbnail")
    private String imageThumbnail;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "price")
    private DecimalFormat price; //***

    @Column(name = "sales")
    private int sales;

    @Column(name = "stock")
    private int stock;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



}
