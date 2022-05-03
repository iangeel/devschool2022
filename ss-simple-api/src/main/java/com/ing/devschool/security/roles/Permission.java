package com.ing.devschool.security.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    MOVE_MONEY("move_money"),
    READ("read_balance"),
    INTERNATIONAL_PAYMENTS("international_payments");

    private final String permission;
}
