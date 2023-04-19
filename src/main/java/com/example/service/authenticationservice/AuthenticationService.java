package com.example.service.authenticationservice;


import com.example.controller.Auth.AuthenticationRequest;
import com.example.controller.Auth.AuthenticationResponse;
import com.example.controller.Auth.RegisterRequest;
import com.example.dao.CustomerDAO;
import com.example.dao.TokenDAO;
import com.example.models.customer.Customer;
import com.example.models.customer.Role;
import com.example.models.token.Token;
import com.example.models.token.TokenType;
import com.example.service.jwtservice.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final CustomerDAO customerDAO;
  private final TokenDAO tokenDAO;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var customer = Customer.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.CUSTOMER)
        .build();
    var savedCustomer = customerDAO.save(customer);
    var jwtToken = jwtService.generateToken(customer);
    var refreshToken = jwtService.generateRefreshToken(customer);
    saveCustomerToken(savedCustomer, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var customer = customerDAO.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(customer);
    var refreshToken = jwtService.generateRefreshToken(customer);
    revokeAllUserTokens(customer);
    saveCustomerToken(customer, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveCustomerToken(Customer customer, String jwtToken) {
    var token = Token.builder()
        .customer(customer)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenDAO.save(token);
  }

  private void revokeAllUserTokens(Customer customer) {
    var validUserTokens = tokenDAO.findAllValidTokenByUser(customer.getCustomerId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenDAO.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var customer = this.customerDAO.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, customer)) {
        var accessToken = jwtService.generateToken(customer);
        revokeAllUserTokens(customer);
        saveCustomerToken(customer, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
