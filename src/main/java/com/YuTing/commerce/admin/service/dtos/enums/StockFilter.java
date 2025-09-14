package com.YuTing.commerce.admin.service.dtos.enums;

import lombok.Getter;

@Getter
public enum StockFilter {
    OUT_OF_STOCK(0, 0),
    RANGE_1_TO_9(1, 9),
    RANGE_10_TO_49(10, 49),
    RANGE_50_PLUS(50, null);

    private final Integer stockFrom;
    private final Integer stockTo;

    StockFilter(Integer stockFrom, Integer stockTo) {
        this.stockFrom = stockFrom;
        this.stockTo = stockTo;
    }

}
