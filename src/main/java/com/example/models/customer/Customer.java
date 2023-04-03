package com.example.models.customer;

import com.example.models.activationToken.ActivationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;
    private String password;
    private boolean isActivated=false;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private ActivationToken activationToken;

}
