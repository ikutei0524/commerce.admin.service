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
    public class UserSegments {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "user_id")
        private int userId;

        @Column(name = "segment_id")
        private int segmentId;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "deleted_at")
        private LocalDateTime deletedAt;


}
