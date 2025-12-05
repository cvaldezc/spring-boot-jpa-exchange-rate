package com.example.rateexchange.thirdparty;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EXCHANGERATES")
@Data
public class ExchangeRates implements Serializable {

  private static final long serialVersionUID = 1L;

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
