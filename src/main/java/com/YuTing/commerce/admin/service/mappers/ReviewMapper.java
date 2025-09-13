package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.requests.ReviewRequest;
import com.YuTing.commerce.admin.service.dtos.responses.ReviewResponse;
import com.YuTing.commerce.admin.service.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review toReview(ReviewRequest request) {
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return review;
    }

    public ReviewResponse toReviewResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setId(response.getId());
        response.setRating(review.getRating());
        response.setStatus(review.getStatus());
        response.setComment(review.getComment());
        response.setCreatedAt(review.getCreatedAt());
        response.setDeleteAt(review.getDeleteAt());
        return response;
    }
}
