package com.example.controller.activationcontroller;

import com.example.models.customer.Customer;
import com.example.service.customerservice.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activate")
@AllArgsConstructor
public class ActivationController {
    private CustomerService customerService;

    @GetMapping("/{activationToken}")
    public Customer activate(@PathVariable String activationToken){
        return customerService.activate(activationToken);
    }
}
