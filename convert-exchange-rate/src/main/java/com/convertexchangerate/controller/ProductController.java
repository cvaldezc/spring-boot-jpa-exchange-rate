package com.convertexchangerate.controller;

import com.convertexchangerate.dto.ProductRequest;
import com.convertexchangerate.repository.entity.Product;
import com.convertexchangerate.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Mono<Product> createProduct(@RequestBody ProductRequest product) {
        return productService.create(product);
    }
}
