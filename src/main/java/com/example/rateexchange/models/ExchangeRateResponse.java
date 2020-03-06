package com.example.rateexchange.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
@Data
public class ExchangeRateResponse {

  private TransactionInformation transactionInformation;

  private Currency currency;

}
