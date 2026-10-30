package com.ly.orderservice.mapper;

import com.ly.orderservice.dto.OrderResponse;
import com.ly.orderservice.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .sku(order.getSku())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();
    }
}
