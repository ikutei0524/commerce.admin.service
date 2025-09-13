package com.YuTing.commerce.admin.service.dtos.responses;

import com.YuTing.commerce.admin.service.dtos.enums.ReviewStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponse {
    private Integer id;
    private UserResponse user;
    private ProductResponse product;
    private int rating;
    private String comment;
    private ReviewStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime deleteAt;
}
