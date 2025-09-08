package com.YuTing.commerce.admin.service.dtos.requests;


import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegmentRequest {
    private String name;
    private String description;
}
