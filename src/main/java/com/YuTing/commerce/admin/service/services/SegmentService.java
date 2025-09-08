package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.dtos.requests.SegmentRequest;
import com.YuTing.commerce.admin.service.dtos.responses.SegmentResponse;
import com.YuTing.commerce.admin.service.mappers.SegmentMapper;
import com.YuTing.commerce.admin.service.model.Segment;
import com.YuTing.commerce.admin.service.repositories.SegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SegmentService {

    private final SegmentRepository segmentRepository;

    public List<SegmentResponse> getAllSegments() {
        return segmentRepository.findByDeletedAtIsNull()
                .stream()
                .map(SegmentMapper::toResponse)
                .toList();
    }

    public SegmentResponse getSegmentById(Integer id) {
        Segment segment = segmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found"));
        return SegmentMapper.toResponse(segment);
    }

    public SegmentResponse createSegment(SegmentRequest request) {
        Segment segment = SegmentMapper.toEntity(request);
        return SegmentMapper.toResponse(segmentRepository.save(segment));
    }

    // 改成接收 DTO
    public SegmentResponse updateSegment(Integer id, SegmentRequest request) {
        Segment segment = segmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found"));

        // 更新欄位
        segment.setName(request.getName());
        segment.setDescription(request.getDescription());

        Segment saved = segmentRepository.save(segment);
        return SegmentMapper.toResponse(saved);
    }

    public void deleteSegment(Integer id) {
        Segment segment = segmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found"));
        segment.setDeletedAt(LocalDateTime.now()); // 標記刪除
        segmentRepository.save(segment);
    }
}
