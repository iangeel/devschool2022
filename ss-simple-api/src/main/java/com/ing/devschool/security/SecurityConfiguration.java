package com.ing.devschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.ing.devschool.security.roles.Permission.INTERNATIONAL_PAYMENTS;
import static com.ing.devschool.security.roles.Permission.MOVE_MONEY;
import static com.ing.devschool.security.roles.Permission.READ;
import static com.ing.devschool.security.roles.Role.BLOCKED_CLIENT;
import static com.ing.devschool.security.roles.Role.CLIENT;
import static com.ing.devschool.security.roles.Role.GOLD_CLIENT;
import static com.ing.devschool.security.roles.Role.NO_KYC_CLIENT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/exchangeRate/**").hasAuthority(READ.getPermission())
                .antMatchers("/payments/pay").hasAuthority(MOVE_MONEY.getPermission())
                .antMatchers("/payments/pay/**").hasAuthority(INTERNATIONAL_PAYMENTS.getPermission())
                .antMatchers("/payments/wallet/**").hasAuthority(READ.getPermission())
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

    }

    @Bean
    public UserDetailsService users() {
        User.UserBuilder builder = User.builder();

        UserDetails user = builder
                .username("user")
                //parola
                .password("{bcrypt}$2a$10$ZiZQgMttbZKGgW2LxDyZwe90cyBNiWXVfgUrKm6ARtJUpjAm5BcCi")
                .authorities(CLIENT.getAuthorities())
                .build();

        UserDetails blockedUser = builder
                .username("blocked_user")
                //parolaparola
                .password("{bcrypt}$2a$10$f2jx.4ErfI9ZHI/lLC9KfOFaFm00h3Q4Uh.yZekkQcRGeOud4SrkO")
                .authorities(BLOCKED_CLIENT.getAuthorities())
                .build();

        UserDetails noKycUser = builder
                .username("no_kyc_user")
                //parola
                .password("{bcrypt}$2a$10$ZiZQgMttbZKGgW2LxDyZwe90cyBNiWXVfgUrKm6ARtJUpjAm5BcCi")
                .authorities(NO_KYC_CLIENT.getAuthorities())
                .build();

        UserDetails goldUser = builder
                .username("gold_user")
                //parolaparola
                .password("{bcrypt}$2a$10$f2jx.4ErfI9ZHI/lLC9KfOFaFm00h3Q4Uh.yZekkQcRGeOud4SrkO")
                .authorities(GOLD_CLIENT.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user, blockedUser, goldUser, noKycUser);
    }
}