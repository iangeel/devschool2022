package com.ing.devschool.security.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    MOVE_MONEY("move_money"),
    READ_BALANCE("read_balance");

    private final String permission;
}
