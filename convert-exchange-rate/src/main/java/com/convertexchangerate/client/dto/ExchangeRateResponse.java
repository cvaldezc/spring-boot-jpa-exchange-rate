package com.convertexchangerate.client.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRateResponse(
        String base,
        String status,
        Map<String, BigDecimal> rates
) {
}
