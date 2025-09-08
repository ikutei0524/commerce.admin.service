package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.model.Segment;
import com.YuTing.commerce.admin.service.repositories.SegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SegmentService {

    private final SegmentRepository segmentRepository;

    public List<Segment> getAllSegments() {
        return segmentRepository.findAll();
    }

    public Segment getSegmentById(Integer id) {
        return segmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found"));
    }

    public Segment createSegment(Segment segment) {
        return segmentRepository.save(segment);
    }

    public Segment updateSegment(Integer id, Segment updatedSegment) {
        Segment segment = segmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found"));
        segment.setName(updatedSegment.getName());
        segment.setDescription(updatedSegment.getDescription());
        return segmentRepository.save(segment);
    }

    public void deleteSegment(Integer id) {
        segmentRepository.deleteById(id);
    }
}
