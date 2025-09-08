package com.YuTing.commerce.admin.service.controllers;

import com.YuTing.commerce.admin.service.model.Segment;
import com.YuTing.commerce.admin.service.services.SegmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/segments")
@RequiredArgsConstructor
@Tag(name = "Segment 管理", description = "管理分群資料")
public class SegmentController {

    private final SegmentService segmentService;

    @GetMapping
    @Operation(summary = "查詢所有分群")
    public List<Segment> getAllSegments() {
        return segmentService.getAllSegments();
    }

    @GetMapping("/{id}")
    @Operation(summary = "查詢指定ID的分群")
    public Segment getSegmentById(@PathVariable Integer id) {
        return segmentService.getSegmentById(id);
    }

    @PostMapping
    @Operation(summary = "新增分群")
    public Segment createSegment(@RequestBody Segment segment) {
        return segmentService.createSegment(segment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新分群")
    public Segment updateSegment(@PathVariable Integer id,
                                 @RequestBody Segment segment) {
        return segmentService.updateSegment(id, segment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除分群")
    public void deleteSegment(@PathVariable Integer id) {
        segmentService.deleteSegment(id);
    }
}
