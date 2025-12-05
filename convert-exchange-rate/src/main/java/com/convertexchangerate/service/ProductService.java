package com.convertexchangerate.service;

import com.convertexchangerate.dto.ProductRequest;
import com.convertexchangerate.repository.entity.Product;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> create(ProductRequest productRequest);
}
