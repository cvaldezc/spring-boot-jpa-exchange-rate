package com.example.rateexchange.business;

import com.example.rateexchange.models.ExchangeRateRequest;
import com.example.rateexchange.models.ExchangeRateResponse;
import io.reactivex.Single;

public interface ExchangeRateService {

  Single<ExchangeRateResponse> getRateExchange(ExchangeRateRequest payload);

}
