package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSegmentResponse {
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    private Integer userId;
    private String username;

    private Integer segmentId;
    private String segmentName;
}
