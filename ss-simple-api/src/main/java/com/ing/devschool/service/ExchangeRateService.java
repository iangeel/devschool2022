package com.ing.devschool.service;

import com.ing.devschool.domain.ExchangeRateResponse;
import com.ing.devschool.domain.PaymentsResponse.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ing.devschool.domain.PaymentsResponse.Currency.EUR;
import static com.ing.devschool.domain.PaymentsResponse.Currency.RON;
import static com.ing.devschool.domain.PaymentsResponse.Currency.USD;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private static final List<String> usdRate = List.of("4.70 Romanian Leu", "0.95 Euro");
    private static final List<String> ronRate = List.of("0.21 United States Dollar", "0.20 Euro");
    private static final List<String> eurRate = List.of("4.95 Romanian Leu", "1.05 United States Dollar");

    private static final Map<Currency, List<String>> exchangeRate = Map.of(
            USD, usdRate,
            EUR, eurRate,
            RON, ronRate);


    public ExchangeRateResponse     getExchangeRate(String currency) {
        return Optional.ofNullable(exchangeRate.get(Currency.valueOf(currency.toUpperCase())))
                .map(ExchangeRateResponse::new)
                .orElseGet(() -> new ExchangeRateResponse(usdRate));
    }

}
