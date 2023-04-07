package com.example.controller.restaurantcontroller;


import com.example.models.restaurant.Restaurant;
import com.example.service.restaurantSerive.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vi/restaurant")
@AllArgsConstructor
public class RestaurantController {
    private RestaurantService  restaurantService;


    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getRestaurants(){
        return restaurantService.getrestaurants();
    }

}
