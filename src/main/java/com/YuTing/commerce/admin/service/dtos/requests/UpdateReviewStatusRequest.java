package com.YuTing.commerce.admin.service.dtos.requests;

import com.YuTing.commerce.admin.service.dtos.enums.ReviewStatus;
import lombok.Data;

import java.util.List;

@Data
public class UpdateReviewStatusRequest {
    private List<Integer> ids;
    private ReviewStatus reviewStatus;
}
