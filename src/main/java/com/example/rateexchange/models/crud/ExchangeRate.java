package com.example.rateexchange.models.crud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ExchangeRate implements Serializable {

  private static final long serialVersionUID = 1L;

  private int id;

  private String origin;

  private String target;

  private BigDecimal rate;

  private boolean active;
}
