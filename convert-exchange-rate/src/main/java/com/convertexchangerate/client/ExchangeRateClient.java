package com.convertexchangerate.client;

import com.convertexchangerate.client.dto.ExchangeRateResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ExchangeRateClient {

    private final WebClient webClient;

    public ExchangeRateClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ExchangeRateResponse> getExchangeRate(String base) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/exchange-rate/"+base)
                        .build())
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class);
    }

}
