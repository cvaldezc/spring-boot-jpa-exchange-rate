package com.example.rateexchange.expose;


import com.example.rateexchange.business.ExchangeRateService;
import com.example.rateexchange.models.ExchangeRateRequest;
import com.example.rateexchange.models.ExchangeRateResponse;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales/v1")
@Slf4j
public class RateExchangeController {

  private ExchangeRateService service;

  public RateExchangeController(ExchangeRateService service) {
    this.service = service;
  }

  @GetMapping("/some")
  public Single<String> getRateExchange() {
    return Single.just("some rate");
  }


  @PostMapping(value = "/rate-exchanges", consumes = {MediaType.APPLICATION_JSON_VALUE},
               produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ExchangeRateResponse> executeExchangeRate(@RequestBody ExchangeRateRequest payload) {
    log.info("{}", payload);
    return service.getRateExchange(payload);
  }

}
