package com.example.service;

import com.example.dao.RestaurantDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantDAO restaurantDAO;


}
