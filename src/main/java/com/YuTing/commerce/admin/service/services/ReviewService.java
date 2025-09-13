package com.YuTing.commerce.admin.service.services;
import com.YuTing.commerce.admin.service.dtos.enums.ReviewStatus;
import com.YuTing.commerce.admin.service.dtos.requests.ReviewRequest;
import com.YuTing.commerce.admin.service.dtos.requests.UpdateReviewStatusRequest;
import com.YuTing.commerce.admin.service.dtos.responses.ReviewResponse;
import com.YuTing.commerce.admin.service.mappers.ProductMapper;
import com.YuTing.commerce.admin.service.mappers.ReviewMapper;
import com.YuTing.commerce.admin.service.mappers.UserMapper;
import com.YuTing.commerce.admin.service.model.Review;
import com.YuTing.commerce.admin.service.repositories.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserService userService;
    private final ProductService productService;

    private final UserMapper userMapper = new UserMapper();

    public ReviewService(
            ReviewRepository reviewRepository,
            ReviewMapper reviewMapper,
            UserService userService,
            ProductService productService
    ) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.userService = userService;
        this.productService = productService;
    }

    public Review getReviewById(int id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found!"));
    }

    public List<Review> getReviewsByIds(List<Integer> ids) {
        return reviewRepository.findAllById(ids);
    }

    public ReviewResponse createReview(ReviewRequest request) {
        Review review = reviewMapper.toReview(request);

        // 避免繞過審核，統一由後端設定 status
        review.setStatus(ReviewStatus.PENDING);
        review.setUser(userService.getUserById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found!")));
        review.setProduct(productService.getProductEntityById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found!")));

        Review createdReview = reviewRepository.save(review);

        ReviewResponse response = reviewMapper.toReviewResponse(createdReview);
        response.setUser(userMapper.toUserResponse(createdReview.getUser()));
        response.setProduct(ProductMapper.toResponse(createdReview.getProduct()));

        return response;
    }

    public List<ReviewResponse> updateReviewStatus(UpdateReviewStatusRequest request) {
        List<Review> reviews = reviewRepository.findAllById(request.getIds());
        if (reviews.isEmpty()) {
            throw new RuntimeException("Reviews not found!");
        }

        reviews.forEach(r -> r.setStatus(request.getReviewStatus()));
        List<Review> updatedReviews = reviewRepository.saveAll(reviews);

        return updatedReviews.stream()
                .map(r -> {
                    ReviewResponse res = reviewMapper.toReviewResponse(r);
                    res.setUser(userMapper.toUserResponse(r.getUser()));
                    res.setProduct(ProductMapper.toResponse(r.getProduct()));
                    return res;
                })
                .toList();
    }



    public Page<ReviewResponse> getReviewPage(int page, int size, String query) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Specification<Review> spec = (root, q, cb) -> {
            if (query == null || query.isEmpty()) {
                return cb.conjunction();
            }
            String likeQuery = "%" + query.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("description")), likeQuery),
                    cb.like(cb.lower(root.get("product").get("description")), likeQuery)
            );
        };

        Page<Review> reviewsPage = reviewRepository.findAll(spec, pageRequest);

        return reviewsPage.map(review -> {
            ReviewResponse response = reviewMapper.toReviewResponse(review);
            response.setUser(userMapper.toUserResponse(review.getUser()));
            response.setProduct(ProductMapper.toResponse(review.getProduct()));
            return response;
        });
    }





}
