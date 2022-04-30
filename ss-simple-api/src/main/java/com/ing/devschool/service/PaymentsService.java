package com.ing.devschool.service;

import com.ing.devschool.domain.Account;
import com.ing.devschool.domain.PaymentRequest;
import com.ing.devschool.domain.PaymentsResponse;
import com.ing.devschool.domain.WalletDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final Map<String, Account> bankAccounts;

    public PaymentsResponse pay(final PaymentRequest paymentRequest) {
        moveMoney(paymentRequest.getFrom(), paymentRequest.getTo(), paymentRequest.getAmount());
        return new PaymentsResponse(bankAccounts.get(paymentRequest.getFrom()).getBalance());
    }

    public WalletDetailsResponse getWalletDetails(final String iban) {
        return ofNullable(bankAccounts.get(iban))
                .map(account -> new WalletDetailsResponse(account.getIban(), account.getBalance()))
                .orElseThrow(() -> new RuntimeException("Error"));
    }

    private void moveMoney(String from, String to, Double value) {
        ofNullable(bankAccounts.get(from))
                .map(account -> bankAccounts.put(account.getIban(),
                        new Account(account.getIban(), account.getBalance() - value)));
        ofNullable(bankAccounts.get(to))
                .map(account -> bankAccounts.put(account.getIban(),
                        new Account(account.getIban(), account.getBalance() + value)));
    }

}
