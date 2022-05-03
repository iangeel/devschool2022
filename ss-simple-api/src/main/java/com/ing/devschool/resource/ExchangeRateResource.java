package com.ing.devschool.resource;

import com.ing.devschool.domain.ExchangeRateResponse;
import com.ing.devschool.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExchangeRateResource {

    private final ExchangeRateService exchangeRateService;

    @GetMapping(path = "/exchangeRate/{currency}")
    @PreAuthorize("hasAuthority('read_balance')")
    public ResponseEntity<ExchangeRateResponse> getWalletDetails(@PathVariable final String currency) {
        return ResponseEntity.status(200).body(exchangeRateService.getExchangeRate(currency));
    }
}
