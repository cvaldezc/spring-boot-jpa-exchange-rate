package com.example.rateexchange.models;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionInformation {

  private BigDecimal amount;

  private BigDecimal amountRate;

  private BigDecimal exchangeRate;

}
