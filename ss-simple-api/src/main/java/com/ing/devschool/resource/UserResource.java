package com.ing.devschool.resource;

import com.ing.devschool.authentication.JwtTokenUtil;
import com.ing.devschool.domain.CredentialsRequest;
import com.ing.devschool.domain.RegistrationRequest;
import com.ing.devschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class UserResource {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegistrationRequest request) {
        userService.saveNewUser(request);
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody CredentialsRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }
}
