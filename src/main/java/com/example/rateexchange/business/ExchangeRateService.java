package com.example.rateexchange.business;

import com.example.rateexchange.models.crud.ExchangeRate;
import com.example.rateexchange.models.post.ExchangeRateRequest;
import com.example.rateexchange.models.post.ExchangeRateResponse;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ExchangeRateService {

  Single<ExchangeRateResponse> getRateExchange(ExchangeRateRequest payload);

  Observable<ExchangeRate> getAllExchangeRates();

  Single<ExchangeRate> patchExchangeRate(ExchangeRate exchangeRate);

}
