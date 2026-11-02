package com.ly.orderservice.service;

import com.ly.orderservice.client.InventoryClient;
import com.ly.orderservice.dto.OrderRequest;
import com.ly.orderservice.dto.OrderResponse;
import com.ly.orderservice.mapper.OrderMapper;
import com.ly.orderservice.model.Order;
import com.ly.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;

    public OrderResponse placeOrder(OrderRequest orderRequest){

        var isProductInStock = inventoryClient.isInStock(orderRequest.sku(), orderRequest.quantity());

        if(isProductInStock){
            Order order = Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .sku(orderRequest.sku())
                    .price(orderRequest.price())
                    .quantity(orderRequest.quantity())
                    .build();

            return orderMapper.toOrderResponse(orderRepository.save(order));
        }
        else {
            throw new RuntimeException("Product not in stock");
        }
    }

}
