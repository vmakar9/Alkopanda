package com.example.controller.customercontroller;



import com.example.service.customerservice.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequestMapping("/api/v1/customer")
@RestController
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;



   @PostMapping("/{customerId}/avatar")
    public ResponseEntity<?> uploadAvatar(@PathVariable int customerId, @RequestParam("avatar")MultipartFile file){
       try {
           String uploadDir = System.getProperty("user.dir") + File.separator+"uploads";
           customerService.saveAvatar(file,uploadDir);

           return ResponseEntity.ok().build();
       }catch (IOException e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }

   @PutMapping("/{customerId}/avatar")
    public ResponseEntity<?> changeAvatar(@PathVariable int customerId,@RequestParam("avatar")MultipartFile file,@RequestParam("filePath") String filePath){
       try {
          customerService.deleteAvatar(filePath);
          customerService.updateAvatar(file,filePath);
          return ResponseEntity.ok().build();
       }catch (IOException e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }

   @DeleteMapping("/{customerId}/avatar")
    public ResponseEntity<?> deleteAvatar(@PathVariable int customerId,@RequestParam("filePath") String filePath){
       try {
           customerService.deleteAvatar(filePath);
           return ResponseEntity.ok().build();
       }catch (IOException e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }

}
