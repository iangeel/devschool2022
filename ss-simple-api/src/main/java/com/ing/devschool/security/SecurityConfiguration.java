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

import static com.ing.devschool.security.roles.Role.BLOCKED_CLIENT;
import static com.ing.devschool.security.roles.Role.CLIENT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/exchangeRate/**").hasAnyRole(CLIENT.name(), BLOCKED_CLIENT.name())
                .antMatchers("/payments/**").hasRole(CLIENT.name())
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
                .roles(CLIENT.name())
                .build();

        UserDetails blockedUser = builder
                .username("blocked_user")
                //parolaparola
                .password("{bcrypt}$2a$10$f2jx.4ErfI9ZHI/lLC9KfOFaFm00h3Q4Uh.yZekkQcRGeOud4SrkO")
                .roles(BLOCKED_CLIENT.name())
                .build();

        return new InMemoryUserDetailsManager(user, blockedUser);
    }
}