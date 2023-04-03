package com.example.service;

import com.example.dao.CustomerDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService  {

    @Autowired
    private CustomerDAO customerDAO;



}
