package com.example.rateexchange.thirdparty;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RateExchangeRepository extends CrudRepository<ExchangeRates, Long> {

  @Query(
      " FROM ExchangeRates WHERE originCurrency = :origin AND targetCurrency = :target")
  ExchangeRates findByCurrencyOriginAndCurrencyTarget(
      @Param("origin") String origin, @Param("target") String target);

}
