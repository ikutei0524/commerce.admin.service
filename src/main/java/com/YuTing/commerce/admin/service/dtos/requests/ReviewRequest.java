package com.YuTing.commerce.admin.service.dtos.requests;

import lombok.Data;

@Data
public class ReviewRequest {
    private int userId;

    private int productId;

    private int rating;

    private String comment;
}
