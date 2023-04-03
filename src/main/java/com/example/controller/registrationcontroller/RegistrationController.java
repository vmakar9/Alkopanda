package com.example.controller.registrationcontroller;

import com.example.models.customer.Customer;
import com.example.service.customerservice.CustomerService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegistrationController {

    private CustomerService customerService;

    @PostMapping
    public Customer register(@RequestBody Customer customer){
        return customerService.register(customer);
    }
}
