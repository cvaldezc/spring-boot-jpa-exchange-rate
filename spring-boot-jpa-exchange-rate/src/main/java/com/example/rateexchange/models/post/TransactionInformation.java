package com.example.rateexchange.models.post;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionInformation {

  private BigDecimal amount;

  private BigDecimal amountRate;

  private BigDecimal exchangeRate;

}
