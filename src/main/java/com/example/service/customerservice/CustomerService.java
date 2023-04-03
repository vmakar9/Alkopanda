package com.example.service.customerservice;

import com.example.dao.CustomerDAO;
import com.example.models.activationToken.ActivationToken;
import com.example.models.customer.Customer;
import com.example.service.mailservice.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class CustomerService  {

    @Autowired
    private CustomerDAO customerDAO;



    @Autowired
    private MailService mailService;

     public Customer register(Customer customer){
         customer.setActivationToken(new ActivationToken());
         return customerDAO.save(customer);
     }

     public Customer activate(String token) {
         Customer customer= customerDAO.findByActivationToken(token);
         if(customer ==  null){
             throw new RuntimeException("Invalid activation token");
         }
         if(customer.getActivationToken().getExpire().isAfter(LocalDateTime.now()) ) {
             customer.setActivated(true);
             customerDAO.save(customer);
         }
         return customer;
     }
}
