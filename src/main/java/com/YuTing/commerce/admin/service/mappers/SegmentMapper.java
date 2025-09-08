package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.requests.SegmentRequest;
import com.YuTing.commerce.admin.service.dtos.responses.SegmentResponse;
import com.YuTing.commerce.admin.service.model.Segment;

public class SegmentMapper {
    public static SegmentResponse toResponse(Segment segment) {
        SegmentResponse response = new SegmentResponse();
        response.setId(segment.getId());
        response.setName(segment.getName());
        response.setDescription(segment.getDescription());
        return response;
    }

    public static Segment toEntity(SegmentRequest request) {
        Segment segment = new Segment();
        segment.setName(request.getName());
        segment.setDescription(request.getDescription());
        return segment;
    }
}
