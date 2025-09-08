package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.dtos.requests.SegmentRequest;
import com.YuTing.commerce.admin.service.dtos.responses.SegmentResponse;
import com.YuTing.commerce.admin.service.services.SegmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Segments")
@RequiredArgsConstructor
@Tag(name = "Segment 管理", description = "管理分群資料")
public class SegmentController {

    private final SegmentService segmentService;

    @GetMapping
    @Operation(summary = "查詢所有分群")
    public ResponseEntity<List<SegmentResponse>> getAllSegments() {
        List<SegmentResponse> segments = segmentService.getAllSegments();
        return ResponseEntity.ok(segments);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查詢指定ID的分群")
    public ResponseEntity<SegmentResponse> getSegmentById(@PathVariable Integer id) {
        SegmentResponse segment = segmentService.getSegmentById(id);
        return ResponseEntity.ok(segment);
    }

    @PostMapping
    @Operation(summary = "新增分群")
    public ResponseEntity<SegmentResponse> createSegment(@RequestBody SegmentRequest request) {
        SegmentResponse segment = segmentService.createSegment(request);
        return ResponseEntity.ok(segment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新分群")
    public ResponseEntity<SegmentResponse> updateSegment(@PathVariable Integer id,
                                                         @RequestBody SegmentRequest request) {
        SegmentResponse segment = segmentService.updateSegment(id, request);
        return ResponseEntity.ok(segment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除分群")
    public ResponseEntity<Void> deleteSegment(@PathVariable Integer id) {
        segmentService.deleteSegment(id);
        return ResponseEntity.noContent().build();
    }
}
