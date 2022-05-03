package com.ing.devschool.configuration;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ing.devschool.domain.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Map<String, Account> bankAccounts() {
        Map<String, Account> bankAccounts = new HashMap<>();

        Account account1 = new Account("1111111111", 10000d);
        Account account2 = new Account("2222222222", 10000d);
        bankAccounts.put(account1.getIban(), account1);
        bankAccounts.put(account2.getIban(), account2);

        return bankAccounts;
    }

    @Bean
    public List<User> users() {
        return new ArrayList<>();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
