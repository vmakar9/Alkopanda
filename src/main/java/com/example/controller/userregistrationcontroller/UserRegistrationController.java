package com.example.controller.userregistrationcontroller;

import com.example.dto.customerregistrationdto.CustomerRegistrationDTO;
import com.example.models.customer.Customer;
import com.example.service.customerservice.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class UserRegistrationController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> registerUser(@RequestBody CustomerRegistrationDTO customerRegistrationDTO){
        Customer customer = customerService.createCustomer(customerRegistrationDTO);
        return new ResponseEntity<>(customer, HttpStatus.valueOf(201));
    }
}
