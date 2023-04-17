package com.example.service.customerservice;

import com.example.dao.CustomerDAO;
import com.example.models.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerDAO customerDAO;

    public void uploadProfilePhoto(int customerId, MultipartFile file) throws IOException {
        Customer customer = customerDAO.findById(customerId).orElseThrow(() ->  new RuntimeException("Customer not found"));
        customer.setProfilePhoto(file.getBytes());
        customerDAO.save(customer);
    }

    public Resource downoloadProfilePhoto(int customerId){
        Customer customer = customerDAO.findById(customerId).orElseThrow(() ->  new RuntimeException("Customer not found"));
        ByteArrayResource resource = new ByteArrayResource(customer.getProfilePhoto());
        return resource;
    }

    public void deleteProfile(int customerId){
        Customer customer = customerDAO.findById(customerId).orElseThrow(() ->  new RuntimeException("Customer not found"));
        customer.setProfilePhoto(null);
        customerDAO.save(customer);
    }


    public void updateCustomerById(Customer customer){
        customerDAO.save(customer);
    }

    public Customer getCustomerById(int customerId){
        return customerDAO.findById(customerId).get();
    }
}
