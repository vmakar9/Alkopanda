package com.example.dto.customerregistrationdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class CustomerRegistrationDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
}
