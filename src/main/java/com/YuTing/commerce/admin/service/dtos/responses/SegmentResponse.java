package com.YuTing.commerce.admin.service.dtos.responses;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegmentResponse {
    private Integer id;
    private String name;
    private String description;


}
