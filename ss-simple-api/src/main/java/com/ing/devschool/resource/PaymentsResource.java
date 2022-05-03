package com.ing.devschool.resource;

import com.ing.devschool.domain.PaymentRequest;
import com.ing.devschool.domain.PaymentsResponse;
import com.ing.devschool.domain.WalletDetailsResponse;
import com.ing.devschool.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentsResource {

    private final PaymentsService paymentsService;

    @PostMapping(path = "/pay")
    @PreAuthorize("hasAuthority('move_money')")
    public ResponseEntity<PaymentsResponse> pay(@RequestBody final PaymentRequest paymentRequest) {
        var response = paymentsService.pay(paymentRequest);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping(path = "/pay/{currency}")
    @PreAuthorize("hasAuthority('international_payments')")
    public ResponseEntity<PaymentsResponse> pay(@RequestBody final PaymentRequest paymentRequest, @PathVariable String currency) {
        var response = paymentsService.pay(paymentRequest, currency);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(path = "/wallet/{iban}")
    @PreAuthorize("hasAuthority('read_balance')")
    public ResponseEntity<WalletDetailsResponse> getWalletDetails(@PathVariable String iban) {
        return ResponseEntity.status(200).body(paymentsService.getWalletDetails(iban));
    }
}
