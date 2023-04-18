package com.example.controller.customercontroller;



import com.example.dao.CustomerDAO;
import com.example.models.customer.Customer;
import com.example.service.customerservice.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequestMapping("/api/v1/customer")
@RestController
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;
    private CustomerDAO customerDAO;

    @PostMapping("/{customerId}/photo")
    public ResponseEntity<Object> uploadProfilePhoto(@PathVariable("customerId") int id, @RequestParam("file")MultipartFile file){
        try {
            customerService.uploadProfilePhoto(id,file);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{customerId}/photo")
    public ResponseEntity<Resource> downoloadProfilePhoto(@PathVariable("customerId") int customerId){
        Resource resource = customerService.downoloadProfilePhoto(customerId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }

    @DeleteMapping("/{customerId}/photo")
    public ResponseEntity<Object> deleteProfilePhoto(@PathVariable("customerId") int customerId){
        customerService.deleteProfile(customerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{customerId}/photo")
    public ResponseEntity<?> updateProfilePhoto(@PathVariable("customerId") int customerId,@RequestParam("file") MultipartFile file){
        Customer customer = customerDAO.findById(customerId).orElseThrow(() ->  new RuntimeException("Customer not found"));
        try {
           customer.setProfilePhoto(file.getBytes());
           customerDAO.save(customer);
           return ResponseEntity.ok().build();
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/{customerId}/info")
    public ResponseEntity<Customer> getInfo(@PathVariable int customerId){
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/{customerId}/info")
    public void updateInfo(@PathVariable int customerId, @RequestBody Customer customer){
        Customer customer1 = customerService.getCustomerById(customerId);
        customer1.setFirstname(customer.getFirstname());
        customer1.setLastname(customer.getLastname());
        customerService.updateCustomerById(customer1);
    }




}
