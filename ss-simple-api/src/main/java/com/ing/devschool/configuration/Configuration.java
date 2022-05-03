package com.ing.devschool.configuration;

import com.ing.devschool.domain.Account;
import com.ing.devschool.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

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
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

//    @Bean
//    public UserDetailsService users() {
//        User.UserBuilder builder = User.builder();
//
//        UserDetails user = builder
//                .username("user")
//                //parola
//                .password("{bcrypt}$2a$10$ZiZQgMttbZKGgW2LxDyZwe90cyBNiWXVfgUrKm6ARtJUpjAm5BcCi")
//                .authorities(CLIENT.getAuthorities())
//                .build();
//
//        UserDetails blockedUser = builder
//                .username("blocked_user")
//                //parolaparola
//                .password("{bcrypt}$2a$10$f2jx.4ErfI9ZHI/lLC9KfOFaFm00h3Q4Uh.yZekkQcRGeOud4SrkO")
//                .authorities(BLOCKED_CLIENT.getAuthorities())
//                .build();
//
//        UserDetails noKycUser = builder
//                .username("no_kyc_user")
//                //parola
//                .password("{bcrypt}$2a$10$ZiZQgMttbZKGgW2LxDyZwe90cyBNiWXVfgUrKm6ARtJUpjAm5BcCi")
//                .authorities(NO_KYC_CLIENT.getAuthorities())
//                .build();
//
//        UserDetails goldUser = builder
//                .username("gold_user")
//                //parolaparola
//                .password("{bcrypt}$2a$10$f2jx.4ErfI9ZHI/lLC9KfOFaFm00h3Q4Uh.yZekkQcRGeOud4SrkO")
//                .authorities(GOLD_CLIENT.getAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(user, blockedUser, goldUser, noKycUser);
//    }
}
