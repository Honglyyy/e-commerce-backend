package com.ly.orderservice.controller;

import com.ly.orderservice.dto.OrderRequest;
import com.ly.orderservice.dto.OrderResponse;
import com.ly.orderservice.mapper.OrderMapper;
import com.ly.orderservice.repository.OrderRepository;
import com.ly.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }
}
