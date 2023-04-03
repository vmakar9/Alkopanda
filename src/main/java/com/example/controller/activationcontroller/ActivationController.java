package com.example.controller.activationcontroller;

import com.example.models.customer.Customer;
import com.example.service.customerservice.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activate")
@AllArgsConstructor
public class ActivationController {
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Customer> activateCustomer(@RequestParam("token") String token){
        Customer customer = customerService.activateCustomer(token);
        if(customer ==  null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
}
