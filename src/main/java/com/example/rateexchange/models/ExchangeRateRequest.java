package com.example.rateexchange.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExchangeRateRequest {

  private Currency currency;

  @Digits(integer = 8, fraction = 4)
  private BigDecimal amount;

}
