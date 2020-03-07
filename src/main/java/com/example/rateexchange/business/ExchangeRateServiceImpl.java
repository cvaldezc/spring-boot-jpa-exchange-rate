package com.example.rateexchange.business;

import com.example.rateexchange.models.crud.ExchangeRate;
import com.example.rateexchange.models.post.Currency;
import com.example.rateexchange.models.post.ExchangeRateRequest;
import com.example.rateexchange.models.post.ExchangeRateResponse;
import com.example.rateexchange.models.post.TransactionInformation;
import com.example.rateexchange.thirdparty.ExchangeRates;
import com.example.rateexchange.thirdparty.RateExchangeRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

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

    return Single.fromCallable(
            () ->
                rateDao.findByOriginCurrencyAndTargetCurrency(
                    payload.getCurrency().getOrigin(), payload.getCurrency().getTarget()))
        .zipWith(Single.just(payload), this::processTransaction)
        .doOnError(ex -> log.error("{}", ex));
  }

  @Override
  public Observable<ExchangeRate> getAllExchangeRates() {
    return Observable.fromIterable(rateDao.findAll())
        .map(this::processExchangeRateSingle)
        .doOnError(ex -> log.error("{}", ex));
  }

  @Override
  public Single<ExchangeRate> patchExchangeRate(ExchangeRate exchangeRate) {

    return Single.fromCallable(() -> updateExchangeRate(exchangeRate))
        .map(rateDao::save)
        .map(this::processExchangeRateSingle)
        .doOnError(ex -> log.error("{}", ex));
  }

  private ExchangeRateResponse processTransaction(
      ExchangeRates source, ExchangeRateRequest payload) {
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

  private ExchangeRate processExchangeRateSingle(ExchangeRates source) throws InterruptedException {
    ExchangeRate res = new ExchangeRate();
    res.setId(source.getId());
    res.setOrigin(source.getOriginCurrency());
    res.setTarget(source.getTargetCurrency());
    res.setRate(source.getRate());
    res.setActive(source.getIsActive() == 1);
    Thread.sleep(3000L);
    return res;
  }

  private ExchangeRates updateExchangeRate(ExchangeRate external) {
    ExchangeRates source = new ExchangeRates();
    source.setId(external.getId());
    source.setOriginCurrency(external.getOrigin());
    source.setTargetCurrency(external.getTarget());
    source.setRate(external.getRate());
    source.setIsActive(external.isActive() ? 1 : 0);
    return source;
  }
}
