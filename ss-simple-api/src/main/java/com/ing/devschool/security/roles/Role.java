package com.ing.devschool.security.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.ing.devschool.security.roles.Permission.*;
import static java.util.Set.of;

@Getter
@RequiredArgsConstructor
public enum Role {
    CLIENT(of(MOVE_MONEY, READ_BALANCE)),
    BLOCKED_CLIENT(of());

    private final Set<Permission> permissions;
}
