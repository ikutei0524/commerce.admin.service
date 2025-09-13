package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.dtos.requests.ReviewRequest;
import com.YuTing.commerce.admin.service.dtos.requests.UpdateReviewStatusRequest;
import com.YuTing.commerce.admin.service.dtos.responses.ReviewResponse;
import com.YuTing.commerce.admin.service.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reviews")
@Tag(name = "評論管理介面", description = "評論相關 API")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(
            summary = "新增評論",
            description = "建立一則新的評論。後端會自動將評論狀態設為 PENDING（待審核），"
                    + "並且綁定對應的使用者與商品，最後回傳建立完成的評論資料。"
    )
    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @Operation(
            summary = "取得評論分頁清單",
            description = "透過分頁方式查詢評論列表。可以指定 page（頁碼）、size（每頁筆數）、"
                    + "以及 query（搜尋字串）。此功能適合在後台管理系統用來顯示評論總覽。"
    )
    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getReviewPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String query
    ) {
        List<ReviewResponse> reviewResponses = reviewService.getReviewPage(page, size, query)
                .getContent();  // 取分頁資料的內容
        return ResponseEntity.ok(reviewResponses);
    }

    @Operation(
            summary = "批次更新評論狀態",
            description = "將多筆評論的狀態一次更新，例如將指定評論設為 APPROVED（已通過）或 REJECTED（已拒絕）。"
                    + "此功能主要用於後台審核流程。"
    )
    @PutMapping("/status")
    public ResponseEntity<List<ReviewResponse>> updateReviewStatus(@RequestBody UpdateReviewStatusRequest request) {
        return ResponseEntity.ok(reviewService.updateReviewStatus(request));
    }
}
