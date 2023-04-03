package com.example.service.customerservice;

import com.example.dao.CustomerDAO;
import com.example.dto.customerregistrationdto.CustomerRegistrationDTO;
import com.example.models.customer.Customer;
import com.example.service.mailservice.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService  {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private MailService mailService;

    public Customer createCustomer(CustomerRegistrationDTO customerRegistrationDTO){
        Customer customer = new Customer();
        customer.setEmail(customerRegistrationDTO.getEmail());
        customer.setPassword(customerRegistrationDTO.getPassword());
        customer.setName(customerRegistrationDTO.getName());
        customer.setSurname(customerRegistrationDTO.getSurname());
        customerDAO.save(customer);

        mailService.send(customer);

        return customer;
    }
    public Customer activateCustomer(String token){
        Customer customer = customerDAO.findByActivationToken(token);
        if(customer !=  null){
            customer.setActivated(true);
            customerDAO.save(customer);
        }
        return customer;
    }
}
