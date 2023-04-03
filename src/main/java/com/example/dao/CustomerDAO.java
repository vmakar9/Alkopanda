package com.example.dao;

import com.example.models.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface CustomerDAO extends JpaRepository<Customer,Integer> {

    Customer findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("select c from Customer c where c.activationToken.token=:t")
    Customer findByActivationToken(String token);
}
