package com.convertexchangerate.service;

import com.convertexchangerate.client.ExchangeRateClient;
import com.convertexchangerate.dto.ProductRequest;
import com.convertexchangerate.repository.ProductRepository;
import com.convertexchangerate.repository.entity.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ExchangeRateClient exchangeRateClient;

    public ProductServiceImpl(ProductRepository productRepository, ExchangeRateClient exchangeRateClient) {
        this.productRepository = productRepository;
        this.exchangeRateClient = exchangeRateClient;
    }

    @Override
    public Mono<Product> create(ProductRequest productRequest) {
        return exchangeRateClient.getExchangeRate(productRequest.currency())
                .switchIfEmpty(Mono.error(new RuntimeException("No Exchange rate available ")))
                .flatMap(rate -> {
                    final var finalPrice = productRequest.price().multiply(rate.rates().get("PEN"));

                    Product product = new Product();
                    product.setName(productRequest.name());
                    product.setOriginalPrice(productRequest.price());
                    product.setFinalPrice(finalPrice);
                    product.setCurrency("PEN");

                    return productRepository.save(product);
                });
    }
}
