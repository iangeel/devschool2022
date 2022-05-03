package com.ing.devschool.security.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.ing.devschool.security.roles.Permission.INTERNATIONAL_PAYMENTS;
import static com.ing.devschool.security.roles.Permission.MOVE_MONEY;
import static com.ing.devschool.security.roles.Permission.READ;
import static java.util.Set.of;

@Getter
@RequiredArgsConstructor
public enum Role {
    GOLD_CLIENT(of(INTERNATIONAL_PAYMENTS, MOVE_MONEY, READ)),
    CLIENT(of(MOVE_MONEY, READ)),
    NO_KYC_CLIENT(of(READ)),
    BLOCKED_CLIENT(of());

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> collect = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        collect.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return collect;
    }
}
