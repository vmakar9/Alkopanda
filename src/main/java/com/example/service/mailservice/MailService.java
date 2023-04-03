package com.example.service.mailservice;

import com.example.models.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;

    public void send(Customer customer){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(customer.getEmail());
            helper.setText("<a href='http://localhost:8080/customers/activate/"+customer.getActivationToken().getToken()+"'>click to activate</a>", true);
            helper.setFrom(new InternetAddress("zmrein212@gmail.com"));
        }catch (MessagingException e){
            throw new RuntimeException(e);
        }
        javaMailSender.send(mimeMessage);
    }
}
