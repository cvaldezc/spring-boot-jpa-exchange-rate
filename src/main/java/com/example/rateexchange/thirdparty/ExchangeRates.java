package com.example.rateexchange.thirdparty;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ExchangeRates")
@Getter
@Setter
public class ExchangeRates implements Serializable {

  private static final long serialVersion = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "origin_currency")
  private String originCurrency;

  @Column(name = "target_currency")
  private String targetCurrency;

  @Column(name = "rate")
  private BigDecimal rate;

  @Column(name = "is_active")
  private int isActive;

}
