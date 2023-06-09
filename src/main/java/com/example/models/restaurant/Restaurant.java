package com.example.models.restaurant;


import com.example.models.customer.Customer;
import com.example.models.photo.Photo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantId;

    private String name;
    private String directorname;
    private String description;
    private Long rating;

    private boolean isParking;

    private boolean isWifi;

    private Long minprice;
    private Long maxprice;

    private Long averageprice;

    @ManyToMany(mappedBy = "favoriteRestaurants")
    private Set<Customer> favoritedByCustomers= new HashSet<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();


}
