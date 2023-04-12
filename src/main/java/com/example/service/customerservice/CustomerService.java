package com.example.service.customerservice;

import com.example.dao.CustomerDAO;
import com.example.models.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerDAO customerDAO;

    public void saveAvatar(MultipartFile file,String uploadDir) throws IOException {
        String filename = file.getOriginalFilename();
        String filepath = Paths.get(uploadDir,filename).toString();
        try (OutputStream os = new FileOutputStream(filepath)){
            os.write(file.getBytes());
        }
    }

    public void deleteAvatar(String filepath) throws IOException{
        Path path = Paths.get(filepath);
        Files.delete(path);
    }

    public void updateAvatar(MultipartFile file,String filepath) throws IOException{
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filepath);
        Files.write(path,bytes);
    }

    public Customer getCustomerById(int customerId){
        return customerDAO.findById(customerId).get();
    }
}
