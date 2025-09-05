package com.YuTing.commerce.admin.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_segments")
@Data
@NoArgsConstructor
@AllArgsConstructor
    public class UserSegment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "segment_id")
        private Segment segment;
        // 多對一：userSegment -> segment

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
        // 多對一：userSegment -> user

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "deleted_at")
        private LocalDateTime deletedAt;



}
