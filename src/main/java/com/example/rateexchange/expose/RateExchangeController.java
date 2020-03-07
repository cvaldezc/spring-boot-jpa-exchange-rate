package com.example.rateexchange.expose;

import com.example.rateexchange.business.ExchangeRateService;
import com.example.rateexchange.models.crud.ExchangeRate;
import com.example.rateexchange.models.post.ExchangeRateRequest;
import com.example.rateexchange.models.post.ExchangeRateResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales/v1")
@Slf4j
public class RateExchangeController {

  private ExchangeRateService service;

  public RateExchangeController(ExchangeRateService service) {
    this.service = service;
  }

  @GetMapping(
      value = "/exchange-rates",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Observable<ExchangeRate> getRateExchanges() {
    return service.getAllExchangeRates();
  }

  @PostMapping(
      value = "/exchange-rates",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Single<ExchangeRateResponse> executeExchangeRate(
      @RequestBody ExchangeRateRequest payload) {
    log.info("{}", payload);
    return service.getRateExchange(payload);
  }

  @PatchMapping(
      value = "/exchange-rates",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
  public Single<ExchangeRate> executePatchExchangeRate(@RequestBody ExchangeRate payload) {
    return service.patchExchangeRate(payload);
  }
}
