package com.example.service;

import com.example.controller.Auth.AuthenticationRequest;
import com.example.controller.Auth.AuthenticationResponse;
import com.example.controller.Auth.RegisterRequest;
import com.example.dao.CustomerDAO;
import com.example.models.customer.Customer;
import com.example.models.customer.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerDAO customerDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
     var customer = Customer.builder()
             .firstname(request.getFirstname())
             .lastname(request.getLastname())
             .email(request.getEmail())
             .password(passwordEncoder.encode(request.getPassword()))
             .role(Role.CUSTOMER)
             .build();
       customerDAO.save(customer);
       var jwtToken = jwtService.generateToken(customer);
       return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
       var customer = customerDAO.findByEmail(request.getEmail())
               .orElseThrow();
        var jwtToken = jwtService.generateToken(customer);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
