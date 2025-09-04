package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;








@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSegmentResponse {
    private int id;
    private String segmentName;
    private LocalDateTime createdAt;
}
