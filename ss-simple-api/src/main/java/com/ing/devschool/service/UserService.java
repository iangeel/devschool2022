package com.ing.devschool.service;

import com.ing.devschool.domain.CredentialsRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final List<User> users;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.stream()
                .filter(user1 -> username.equals(user1.getUsername()))
                .findAny()
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s doesn't exists.", username)));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    public void saveNewUser(CredentialsRequest credentials) {
        users.add(new User(credentials.getUsername(), passwordEncoder.encode(credentials.getPassword()), Collections.emptyList()));
    }
}