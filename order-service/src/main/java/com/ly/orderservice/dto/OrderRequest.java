package com.ly.orderservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderRequest(
        Long id,
        String orderNumber,
        String sku,
        BigDecimal price,
        Integer quantity
) {
}
