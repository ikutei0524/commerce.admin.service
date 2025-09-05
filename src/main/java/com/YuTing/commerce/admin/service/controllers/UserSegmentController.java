package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.model.UserSegment;
import com.YuTing.commerce.admin.service.services.UserSegmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserSegments")
@RequiredArgsConstructor
@Tag(name = "User-Segment Management", description = "管理使用者與分群的關聯")
public class UserSegmentController {

    private final UserSegmentService userSegmentService;

    @PostMapping("/{userId}/{segmentId}")
    @Operation(summary = "把使用者加入某個分群")
    public ResponseEntity<UserSegment> addUserToSegment(@PathVariable Integer userId,
                                                        @PathVariable Integer segmentId) {
        return ResponseEntity.ok(userSegmentService.addUserToSegment(userId, segmentId));
    }

    @DeleteMapping("/{userId}/{segmentId}")
    @Operation(summary = "從某個分群移除使用者")
    public ResponseEntity<Void> removeUserFromSegment(@PathVariable Integer userId,
                                                      @PathVariable Integer segmentId) {
        userSegmentService.removeUserFromSegment(userId, segmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "查詢某使用者的所有分群")
    public ResponseEntity<List<UserSegment>> getSegmentsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userSegmentService.getSegmentsByUser(userId));
    }

    @GetMapping("/segment/{segmentId}")
    @Operation(summary = "查詢某分群的所有使用者")
    public ResponseEntity<List<UserSegment>> getUsersBySegment(@PathVariable Integer segmentId) {
        return ResponseEntity.ok(userSegmentService.getUsersBySegment(segmentId));
    }
}
