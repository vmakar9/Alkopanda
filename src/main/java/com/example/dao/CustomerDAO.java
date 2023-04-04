package com.example.dao;

import com.example.models.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerDAO extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByEmail(String email);
}
