package com.ly.productservice.service;

import com.ly.productservice.dto.ProductRequest;
import com.ly.productservice.dto.ProductResponse;
import com.ly.productservice.mapper.ProductMapper;
import com.ly.productservice.model.Product;
import com.ly.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("product create successfully");

        return productMapper.toDto(product);
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }
}
