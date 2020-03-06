package com.example.rateexchange.business;

import com.example.rateexchange.models.Currency;
import com.example.rateexchange.models.ExchangeRateRequest;
import com.example.rateexchange.models.ExchangeRateResponse;
import com.example.rateexchange.models.TransactionInformation;
import com.example.rateexchange.thirdparty.ExchangeRates;
import com.example.rateexchange.thirdparty.RateExchangeRepository;

import io.reactivex.Single;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private RateExchangeRepository rateDao;

  public ExchangeRateServiceImpl(RateExchangeRepository rateExchanges) {
    this.rateDao = rateExchanges;
  }

  @Override
  public Single<ExchangeRateResponse> getRateExchange(ExchangeRateRequest payload) {
    
    return Single
        .fromCallable(() -> rateDao
        .findByOriginCurrencyAndTargetCurrency(payload.getCurrency().getOrigin(), payload.getCurrency().getTarget())
        )
        .zipWith(Single.just(payload), this::processTransaction)
        .doOnError(ex -> log.error("{}", ex));
  }

  private ExchangeRateResponse processTransaction(ExchangeRates source, ExchangeRateRequest payload) {
    ExchangeRateResponse response = new ExchangeRateResponse();
    Currency currency = new Currency();
    currency.setOrigin(source.getOriginCurrency());
    currency.setTarget(source.getTargetCurrency());
    response.setCurrency(currency);
    // transaction
    BigDecimal amountRate = source.getRate().multiply(payload.getAmount());
    TransactionInformation tx = new TransactionInformation();
    tx.setAmount(payload.getAmount());
    tx.setAmountRate(amountRate);
    tx.setExchangeRate(source.getRate());
    response.setTransactionInformation(tx);
    return response;
  }
}
