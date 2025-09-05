package com.YuTing.commerce.admin.service.dtos.responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> items;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
