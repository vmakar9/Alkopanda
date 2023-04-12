package com.example.controller.favoriterestaurantcontroller;

import com.example.dao.CustomerDAO;
import com.example.models.customer.Customer;
import com.example.models.restaurant.Restaurant;
import com.example.service.favoriterestaurantservice.FavoriteRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/customer/{customerId}/favorite-restaurants")
@AllArgsConstructor
public class FavoriteRestaurantController {
    private FavoriteRestaurantService favoriteRestaurantService;
    private CustomerDAO customerDAO;

    @GetMapping("")
    public Set<Restaurant> getFavorites(@PathVariable int customerId){
        Customer customer = customerDAO.findById(customerId).orElseThrow(() ->  new RuntimeException("Customer not found"));
        return customer.getFavoriteRestaurants();
    }



    @PostMapping("/{restaurantId}")
    public void addToFavorites(@PathVariable int customerId,@PathVariable int restaurantId){
        favoriteRestaurantService.addToFavorites(customerId,restaurantId);
    }

    @DeleteMapping("/{restaurantId")
    public void removeFromFavorites(@PathVariable int userId, @PathVariable int restaurantId) {
        favoriteRestaurantService.removeFromFavorites(userId, restaurantId);
    }



}
