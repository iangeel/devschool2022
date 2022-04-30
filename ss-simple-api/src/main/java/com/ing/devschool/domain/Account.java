package com.ing.devschool.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Account {

    private final String iban;
    private final double balance;
}
