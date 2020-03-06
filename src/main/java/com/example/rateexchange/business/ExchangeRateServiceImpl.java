package com.example.rateexchange.business;

import com.example.rateexchange.models.Currency;
import com.example.rateexchange.models.ExchangeRateRequest;
import com.example.rateexchange.models.ExchangeRateResponse;
import com.example.rateexchange.thirdparty.ExchangeRates;
import com.example.rateexchange.thirdparty.RateExchangeRepository;
import io.reactivex.Single;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private RateExchangeRepository rateDao;

  public ExchangeRateServiceImpl(RateExchangeRepository rateExchanges) {
    this.rateDao = rateExchanges;
  }

  @Override
  public Single<ExchangeRateResponse> getRateExchange(ExchangeRateRequest payload) {
    log.info(payload.getAmount().toString());
    log.info(payload.getCurrency().getOrigin());
    log.info("{}", rateDao
    .findByCurrencyOriginAndCurrencyTarget(payload.getCurrency().getOrigin(), payload.getCurrency().getTarget()));
    return Single
        .fromCallable(() -> rateDao
        .findByCurrencyOriginAndCurrencyTarget(payload.getCurrency().getOrigin(), payload.getCurrency().getTarget())
        )
        .map(this::processTransaction)
        .doOnError(ex -> log.error("{}", ex));
  }

  private ExchangeRateResponse processTransaction(ExchangeRates source) {
    ExchangeRateResponse res = new ExchangeRateResponse();
    Currency currency = new Currency();
    currency.setOrigin(source.getOriginCurrency());
    currency.setTarget(source.getTargetCurrency());
    res.setCurrency(currency);
    return res;
  }
}
