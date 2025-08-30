package com.YuTing.commerce.admin.service.model;


import jakarta.persistence.*;
import com.YuTing.commerce.admin.service.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "user")
    private List<UserSegment> userSegments;
    // 一對多：user -> userSegments

    @OneToMany(mappedBy = "user")
    private List<Order>orders;
    // 一對多：user -> orders

    @OneToMany(mappedBy = "user")
    private List<Review>reviews;
    // 一對多：user -> reviews

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "password")
    private String password;

    @Column(name = "has_newsletter")
    private boolean hasNewsletter;

    @Column(name = "last_seen_at")
    private LocalDateTime lastSeenAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "role")
    private String role;


}
