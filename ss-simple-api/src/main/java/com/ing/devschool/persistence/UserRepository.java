package com.ing.devschool.persistence;

import com.ing.devschool.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ing.devschool.security.roles.Role.CLIENT;
import static com.ing.devschool.security.roles.Role.GOLD_CLIENT;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final PasswordEncoder passwordEncoder;

    public Optional<User> selectUserFromDb(String username) {
        final User userDb = User.builder()
                .username("angel")
                .password(passwordEncoder.encode("parola"))
                .authorities(GOLD_CLIENT.getAuthorities())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .build();


        final List<User> users = List.of(
                userDb,
                userDb.toBuilder()
                        .username("nicu")
                        .authorities(CLIENT.getAuthorities())
                        .build()
        );

        return users.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }
}
