package com.ly.productservice.mapper;

import com.ly.productservice.dto.ProductResponse;
import com.ly.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toDto(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
