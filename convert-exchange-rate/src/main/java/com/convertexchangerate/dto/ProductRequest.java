package com.convertexchangerate.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        BigDecimal price,
        String currency
) {
}
